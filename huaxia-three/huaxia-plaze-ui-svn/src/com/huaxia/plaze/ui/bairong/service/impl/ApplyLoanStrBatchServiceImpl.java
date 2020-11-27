package com.huaxia.plaze.ui.bairong.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatch;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchDetail;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchFile;
import com.huaxia.plaze.ui.bairong.mapper.ApplyLoanStrBatchDetailMapper;
import com.huaxia.plaze.ui.bairong.mapper.ApplyLoanStrBatchFileMapper;
import com.huaxia.plaze.ui.bairong.mapper.ApplyLoanStrBatchMapper;
import com.huaxia.plaze.ui.bairong.mapper.ApplyLoanStrConfigurateMapper;
import com.huaxia.plaze.ui.bairong.service.ApplyLoanStrBatchService;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.IDUtils;

@Service("applyLoanStrTrnBatchService")
public class ApplyLoanStrBatchServiceImpl implements ApplyLoanStrBatchService {

	private final static Logger logger = LoggerFactory.getLogger(ApplyLoanStrBatchServiceImpl.class);

	@Resource
	private DataSourceTransactionManager transactionManager;

	@Resource
	private ApplyLoanStrBatchMapper applyLoanStrTrnBatchMapper;

	@Resource
	private ApplyLoanStrBatchFileMapper applyLoanStrTrnBatchFileMapper;

	@Resource
	private ApplyLoanStrBatchDetailMapper applyLoanStrTrnBatchDetailMapper;

	@Resource
	private ApplyLoanStrConfigurateMapper applyLoanStrConfigurateMapper;

	public String isAllowBatchQuery() {
		Map<String, Object> args = new HashMap<>();

		args.put("sourceCode", "000700");
		args.put("channelCode", "01");

		QueryConfiguration queryCount = applyLoanStrConfigurateMapper.selectSettingByTaskType(args);
		if (queryCount != null) {
			Date date = new Date();
			if (date.getTime() < queryCount.getStartTime().getTime()
					|| date.getTime() > queryCount.getEndTime().getTime()) {
				return "该时间段不能查询";
			}
			args.clear();
			args.put("startTime", queryCount.getStartTime());
			args.put("endTime", queryCount.getEndTime());
			args.put("queryFrom", "01");

			int count = applyLoanStrConfigurateMapper.selectBatchCountByReferTime(args);
			if (count > queryCount.getMaxCount()) {
				return "查询数量已达上限";
			}
		}

		return null;
	}

	/* 批量实时查询--解析批次文件数据入库 */
	@Deprecated
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveObject(MultipartFile[] files, String batchNo, User loginUser) throws Exception {
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 文件名
		String name = null;
		// 记录文件的总行数
		int i = 0;
		// 记录所有文件的总行数
		int allLine = 0;
		try {
			// 查询证件类型
			for (MultipartFile file : files) {
				ApplyLoanStrBatchFile fileDetail = new ApplyLoanStrBatchFile();
				// 获取一个文件的uuid
				String fileId = SequenceUtil.getUUID();
				i = 0;
				// 文件名
				name = file.getOriginalFilename();
				if (name.indexOf("txt") != -1) {
					br = null;
					br = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
					record = br.readLine();
					while (StringUtils.isNotBlank(record)) {
						ApplyLoanStrBatchDetail detail = new ApplyLoanStrBatchDetail();
						i++;
						String[] strArray = record.split("\\|");
						if (strArray.length != 5) {
							throw new ApplicationException("文件" + name + "第" + i + "行格式不对");
						}
						// 批次文件中的每个查询入库
						detail.setCrtUser(loginUser.getAccount());
						detail.setBatchFileId(fileId);
						detail.setTrnId(GuidUtil.getGuid());
						detail.setCertType("00");
						if (!IDUtils.isIDNumber(strArray[1])) {
							throw new ApplicationException("文件" + name + "第" + i + "行身份证号不对");
						}
						if (!IDUtils.validateMobilePhone(strArray[2])) {
							throw new ApplicationException("文件" + name + "第" + i + "行手机号不对");
						}
						if (strArray[3].length() > 12) {
							throw new ApplicationException("文件" + name + "第" + i + "行账户号长度超过12位，格式不对");
						}
						if (!IDUtils.isNumber(strArray[4])) {
							throw new ApplicationException("文件" + name + "第" + i + "行序号不是纯数字，格式不对");
						}
						detail.setName(strArray[0]);
						detail.setCertNo(strArray[1]);
						detail.setMobile(strArray[2]);
						detail.setCardAccount(strArray[3]);
						detail.setSequence(Long.parseLong(strArray[4]));
						detail.setQueryStatus("0");
						applyLoanStrTrnBatchDetailMapper.insertObject(detail);
						record = br.readLine();
					}

					if (i > 20000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于20000");
					}
				} else {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount;
					if (i > 20000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于20000");
					}
					// 遍历每一行 第一行为标题行
					for (int r = 0; r < rowCount; r++) {
						ApplyLoanStrBatchDetail detail = new ApplyLoanStrBatchDetail();
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						if (cellCount != 5) {
							throw new ApplicationException("文件" + name + "第" + (r + 1) + "行超过最大列数3");
						}
						// 遍历每一列
						for (int c = 0; c < cellCount; c++) {
							Cell cell = row.getCell(c);// 获得列值
							if (cell != null) {
								switch (c) {
								case 0: // 证件号
									detail.setName(cell.toString());
									break;
								case 1:
									if (!IDUtils.isIDNumber(cell.toString())) {
										throw new ApplicationException("文件" + name + "第" + i + "行身份证号不对");
									}
									detail.setCertNo(cell.toString());
									break;
								case 2:
									if (!IDUtils.validateMobilePhone(cell.toString())) {
										throw new ApplicationException("文件" + name + "第" + i + "行手机号不对");
									}
									detail.setMobile(cell.toString());
									break;
								case 3:
									if (cell.toString().length() > 12) {
										throw new ApplicationException("文件" + name + "第" + i + "行账户号长度超过12位，格式不对");
									}
									detail.setCardAccount(cell.toString());
								case 4:
									if (!IDUtils.isNumber(cell.toString())) {
										throw new ApplicationException("文件" + name + "第" + i + "行序号不是纯数字，格式不对");
									}
									detail.setSequence(Long.parseLong(cell.toString()));
									break;
								}
							}
						}
						// 批次文件中的每个查询入库
						detail.setCrtUser(loginUser.getAccount());
						detail.setBatchFileId(fileId);
						detail.setTrnId(GuidUtil.getGuid());
						detail.setCertType("00");
						detail.setQueryStatus("0");
						applyLoanStrTrnBatchDetailMapper.insertObject(detail);
					}
				}
				if (i == 0) {
					throw new ApplicationException("文件" + name + "是空文件");
				}
				allLine += i;

				// 各个批次文件入库
				fileDetail.setCrtUser(loginUser.getAccount());
				fileDetail.setBatchId(batchNo);
				fileDetail.setBatchFileId(fileId);
				fileDetail.setBatchFileName(name);
				fileDetail.setBatchFileRecordSize(i);
				applyLoanStrTrnBatchFileMapper.insertObject(fileDetail);
			}
			// 创建这个批次的存储数据，创建批次表，批次表入库
			ApplyLoanStrBatch applyLoanStrTrnBatch = new ApplyLoanStrBatch();
			applyLoanStrTrnBatch.setCrtUser(loginUser.getAccount());
			applyLoanStrTrnBatch.setBatchId(batchNo);
			applyLoanStrTrnBatch.setBatchRecordSize(allLine);
			applyLoanStrTrnBatchMapper.insertObject(applyLoanStrTrnBatch);
			// 查询接口设置，不设置数量不限制查询
			String resultInterface = isAllowBatchQuery();
			if (resultInterface != null) {
				throw new ApplicationException(resultInterface);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				throw e;
			}
			throw new Exception("请求异常");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return "success";
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public int saveUploadRecord(MultipartFile file, String batchNo, String account) throws Exception {
		ApplyLoanStrBatchFile fileDetail = new ApplyLoanStrBatchFile();
		String fileId = SequenceUtil.getUUID();
		String fileName = file.getOriginalFilename();
		if (fileName.endsWith(".txt")) {
			int rows = 1;
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
			String record = reader.readLine();
			while (StringUtils.isNotBlank(record)) {
				if (rows > 20000) {
					throw new Exception("文件[ " + fileName + " ]数量超过最大限制");
				}
				
				ApplyLoanStrBatchDetail detail = new ApplyLoanStrBatchDetail();
				detail.setCrtUser(account);
				detail.setBatchFileId(fileId);
				detail.setTrnId(GuidUtil.getGuid());
				detail.setCertType("00");
				
				// 数据记录校验
				String[] columns = record.split("\\|");
				if (columns.length != 5) {
					throw new Exception("文件" + fileName + "第" + rows + "行格式校验失败");
				}
				if (columns[1].length() > 32) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 证件号码 ] 长度校验失败");
				}
				if (columns[2].length() > 32) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 手机号码 ] 长度校验失败");
				}
				if (columns[3].length() > 12) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 开卡账号 ] 长度校验失败");
				}
				if (columns[4].length() > 12) {
					throw new Exception("文件" + fileName + "第" + rows + "行 [ 序号 ] 长度校验失败");
				}
				
				detail.setName(columns[0]);
				detail.setCertNo(columns[1]);
				detail.setMobile(columns[2]);
				detail.setCardAccount(columns[3]);
				detail.setSequence(Long.parseLong(columns[4]));
				detail.setQueryStatus("0");
				
				// 数据记录信息持久化
				rows += applyLoanStrTrnBatchDetailMapper.insertObject(detail);
				record = reader.readLine();
			}
			
			// 恢复预读取多读的一行即为最终记录总数
			rows--;
			
			// 数据文件信息持久化
			fileDetail.setCrtUser(account);
			fileDetail.setBatchId(batchNo);
			fileDetail.setBatchFileId(fileId);
			fileDetail.setBatchFileName(fileName);
			fileDetail.setBatchFileRecordSize(rows);
			applyLoanStrTrnBatchFileMapper.insertObject(fileDetail);
			
			return rows;
		}
		return 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatch(ApplyLoanStrBatch applyLoanStrTrnBatch) {
		return applyLoanStrTrnBatchMapper.insertObject(applyLoanStrTrnBatch);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatchFile(ApplyLoanStrBatchFile applyLoanStrTrnBatchFile) {
		return applyLoanStrTrnBatchFileMapper.insertObject(applyLoanStrTrnBatchFile);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveTrnBatchDetail(ApplyLoanStrBatchDetail applyLoanStrTrnBatchDetail) {
		return applyLoanStrTrnBatchDetailMapper.insertObject(applyLoanStrTrnBatchDetail);
	}

	/**
	 * 查询批次表
	 * 
	 * @param args
	 * @return
	 */
	@Override
	public List<ApplyLoanStrBatch> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return applyLoanStrTrnBatchMapper.selectBatchListByPage(args);
	}

	/**
	 * 查询总记录数目
	 * 
	 * @param args
	 * @return
	 */
	@Override
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return applyLoanStrTrnBatchMapper.selectBatchListCountByPage(args);
	}

	@Override
	public List<ApplyLoanStrBatchFile> queryBatchFileListByPage(String batchId) {
		return applyLoanStrTrnBatchFileMapper.selectBatchFileListByPage(batchId);
	}

	@Override
	public List<ApplyLoanStrBatchDetail> queryBatchDetailListByPage(String batchFileId) {
		return applyLoanStrTrnBatchDetailMapper.selectBatchDetailListByPage(batchFileId);
	}
}
