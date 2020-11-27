package com.huaxia.plaze.ui.numread.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.numread.domain.NumReadBatch;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchDetail;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchFile;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchResponse;
import com.huaxia.plaze.ui.numread.domain.NumReadLog;
import com.huaxia.plaze.ui.numread.mapper.BatchDetailMapper;
import com.huaxia.plaze.ui.numread.mapper.BatchFileMapper;
import com.huaxia.plaze.ui.numread.mapper.BatchMapper;
import com.huaxia.plaze.ui.numread.mapper.BatchLogMapper;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.GuidUtil;

/**
 * 人行数字解读 批量
 * @author liyanjie
 *
 */
@Service("batchService")
public class BatchServiceImpl implements BatchService {
	
	private final static Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);
	@Resource
	private BatchDetailMapper batchDetailMapper;
	@Resource
	private BatchFileMapper batchFileMapper;
	@Resource
	private BatchMapper batchMapper;
	@Resource
	private BatchLogMapper batchLogMapper;
	

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public String saveBatch(String batchNo, MultipartFile[] files, User loginUser) {
		//定义文件输入流
		BufferedReader br = null;
		//读取txt文件每一行记录
		String record = null;
		//文件名
		String name = null;
		List<NumReadBatchDetail> detailList = new ArrayList<>();
		List<NumReadBatchFile> fileList = new ArrayList<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		//记录文件的总行数
		int i = 0;
		// 记录所有文件的总行数
		int allLine = 0;
		try {
			for(MultipartFile file : files){
				//获取一个文件的uuid
				String fileId = SequenceUtil.getUUID();
				i=0;
				name = file.getOriginalFilename();
				if(name.indexOf("txt")!=-1){
					br = null;
					br = new BufferedReader(new InputStreamReader(file.getInputStream(),"GBK"));
					record = br.readLine();
					while(StringUtils.isNotBlank(record)){
						i++;
						String[] strArray = record.split("\\|");
						if(strArray.length != 4){
							throw new ApplicationException("文件"+name+"第"+i+"行格式不对");
						}
						NumReadBatchDetail detail = new NumReadBatchDetail();
						String trnId = GuidUtil.getGuid();
						detail.setTrnId(trnId);
						detail.setName(strArray[0]);
						detail.setCertType(strArray[1]);
						detail.setCertNo(strArray[2]);
						detail.setProductDate(strArray[3]);
						detail.setStatus("0");
						detail.setQueryReason("01");
						detail.setFileId(fileId);
						detail.setCrtUser(loginUser.getAccount());
						detail.setBatchNo(batchNo);
						detailList.add(detail);
						record = br.readLine();
					}
					if(i > 10000){
						logger.info("文件{}导入数量不能大于10000条", name);
						return "文件" + name + "导入数量不能大于10000条";
					}
				}else{
					Workbook workbook = WorkbookFactory.create(file.getInputStream());
					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount-1;
					if (i > 10000) {
						logger.info("文件{}导入数量不能大于10000", name);
						return "文件" + name + "导入数量不能大于10000条";
					}
					//遍历每一行 第一行为标题行
					for(int r = 1;r<rowCount; r++){
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						if (cellCount != 4) {
							throw new ApplicationException("文件" + name + "第" + (r + 1) + "行超过最大列数4");
						}
						NumReadBatchDetail detail = new NumReadBatchDetail();
						//遍历每一列
						for (int c = 0; c < cellCount; c++) {
							Cell cell = row.getCell(c);// 获得列值
							if(cell != null){
								switch(c){
								case 0:
									detail.setName(cell.toString());
									break;
								case 1:
									detail.setCertType(cell.toString());
									break;
								case 2:
									detail.setCertNo(cell.toString());
									break;
								case 3:
//									detail.setProductDate(fmt.format(cell.getDateCellValue()));
									detail.setProductDate(cell.toString());
									break;
								}
							}
						}
						String trnId = GuidUtil.getGuid();
						detail.setTrnId(trnId);
						detail.setQueryReason("01");
						detail.setStatus("0");
						detail.setFileId(fileId);
						detail.setBatchNo(batchNo);
						detail.setCrtUser(loginUser.getAccount());
						detailList.add(detail);
					}
				}
				if(1 == 0){
					return "文件" + name + "是空文件";
				}
				allLine += i;
				NumReadBatchFile batchFile = new NumReadBatchFile();
				batchFile.setBatchNo(batchNo);
				batchFile.setBatchFileName(name);
				batchFile.setBatchFileId(fileId);
				batchFile.setBatchFileRecordSize(i);
				batchFile.setCrtUser(loginUser.getAccount());
				fileList.add(batchFile);
			}
			if(detailList != null && detailList.size() > 0){
				for(NumReadBatchDetail detail : detailList){
					NumReadLog log = new NumReadLog();
					log.setCertNo(detail.getCertNo());
					log.setCrtUser(loginUser.getAccount());
					log.setName(detail.getName());
					log.setTrnId(detail.getTrnId());
					log.setQueryModel("2");
					batchLogMapper.saveBatchLog(log);
					batchDetailMapper.saveNumReadBatchDetail(detail);
				}
			}
			if (fileList != null && fileList.size() > 0) {
				for(NumReadBatchFile file : fileList){
					batchFileMapper.saveNumReadBatchFile(file);
				}
			}
			NumReadBatch batch = new NumReadBatch();
			batch.setBatchNo(batchNo);
			batch.setBatchRecordSize(allLine);
			batch.setCrtUser(loginUser.getAccount());
			batch.setStaffId(loginUser.getStaffId());
			batchMapper.saveNumReadBatch(batch);
			
		} catch (Exception e) {
			if (e instanceof ApplicationException) {
				return e.getMessage();
			}
			logger.error(e.getMessage(), e);
			return "提交失败";
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("资源关闭异常",e);
				}
			}
		}
		return "提交成功    生成批次号为" + batchNo;
	}

	/**
	 * 查询 批次数量
	 */
	@Override
	public int queryBatchListCountByPage(PageProperty page) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.putAll(page.getDataMap());
		try {
			return batchMapper.selectBatchListCountByPage(args);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 查询批次 分页数据
	 */
	@Override
	public List<NumReadBatch> queryBatchListByPage(PageProperty page) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		try {
			List<NumReadBatch> list = batchMapper.selectBatchListByPage(page);
			if(list!=null && list.size()>0){
				for(NumReadBatch batch :list){
					if(batch.getBatchRecordSize() == batch.getStatus0()){
						batch.setStatus("未查询");
					}else if(batch.getBatchRecordSize() == batch.getStatus3()){
						batch.setStatus("查询完成");
					}else{
						batch.setStatus("正在查询");
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 查询批次 对应的 文件数据
	 */
	@Override
	public List<NumReadBatchFile> queryFileDetail(String batchId) {
		try {
			List<NumReadBatchFile> list= batchFileMapper.selectFileDetail(batchId);
			if(list!=null && list.size()>0){
				for(NumReadBatchFile batch :list){
					if(batch.getBatchFileRecordSize() == batch.getStatus0()){
						batch.setQueryStatus("未查询");
					}else if(batch.getBatchFileRecordSize() == batch.getStatus3()){
						batch.setQueryStatus("查询完成");
					}else{
						batch.setQueryStatus("正在查询");
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 查询批次文件中数据明细
	 */
	@Override
	public List<NumReadBatchDetail> selectDetail(PageProperty page) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		try {
			return batchDetailMapper.selectDetail(args);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 查询文件中的数据量
	 */
	@Override
	public int selectCountDetail(String batchFileId) {
		try {
			return batchDetailMapper.selectCountDetail(batchFileId);
		} catch (Exception e) {
			logger.error("人行数字解读查询批次文件中的数据量失败！！", e);
		}
		return 0;
	}

	@Override
	public NumReadBatchResponse selectDetailResponseByTrnId(String trnId) {
		try {
			return batchDetailMapper.selectDetailResponseByTrnId(trnId);
		} catch (Exception e) {
			logger.error("根据trnId查询数字解读详细信息异常", e);
		}
		return null;
	}
	/**
	 * 根据证件号和查询成功的状态查询历史数据
	 */
	@Override
	public List<NumReadBatchDetail> selectDetailByCertNoAndSuccess(PageProperty page) {
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		try {
			return batchDetailMapper.selectDetailByCertNoAndSuccess(args);
		} catch (Exception e) {
			logger.error("查询人行数字解读历史数据失败", e);
		}
		return null;
	}
	/**
	 * 统计所有查询成功的数量
	 */
	@Override
	public int selectCountDetailSuccess(String certNo) {
		try {
			return batchDetailMapper.selectCountDetailSuccess(certNo);
		} catch (Exception e) {
			logger.error("人行数字解读历史查询统计成功数量错误", e);
		}
		return 0;
	}
	/**
	 * 历史查询记录日志
	 */
	@Override
	public int saveLog(String certNo, User loginUser) {
		try {
			NumReadLog log = new NumReadLog();
			log.setCertNo(certNo);
			log.setCrtUser(loginUser.getAccount());
			log.setName("");
			log.setTrnId("");
			log.setQueryModel("3");
			batchLogMapper.saveBatchLog(log);
		} catch (Exception e) {
			logger.error("记录历史查询日志失败", e);
		}
		return 0;
	}

}
