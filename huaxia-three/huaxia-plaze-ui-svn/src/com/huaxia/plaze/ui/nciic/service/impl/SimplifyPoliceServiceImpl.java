package com.huaxia.plaze.ui.nciic.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.nciic.domain.NciicBatchReview;
import com.huaxia.plaze.ui.nciic.domain.NciicInfo;
import com.huaxia.plaze.ui.nciic.domain.NciicXpInfo;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatch;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchDetail;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchFile;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceQueryLog;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceSingle;
import com.huaxia.plaze.ui.nciic.mapper.NciicInfoMapper;
import com.huaxia.plaze.ui.nciic.mapper.SimplifyPoliceBatchMapper;
import com.huaxia.plaze.ui.nciic.mapper.SimplifyPoliceQueryLogMapper;
import com.huaxia.plaze.ui.nciic.mapper.SimplifyPoliceSingleMapper;
import com.huaxia.plaze.ui.nciic.mapper.XpConfigurateMapper;
import com.huaxia.plaze.ui.nciic.mapper.XpInfoMapper;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.plaze.ui.system.domain.User;

@Service("simplifyPoliceService")
public class SimplifyPoliceServiceImpl implements SimplifyPoliceService {

	private final static Logger logger = LoggerFactory.getLogger(SimplifyPoliceServiceImpl.class);

	@Resource
	private SimplifyPoliceSingleMapper simplifyPoliceSingleMapper;

	@Resource
	private SimplifyPoliceBatchMapper simplifyPoliceBatchMapper;

	@Resource
	private SimplifyPoliceQueryLogMapper simPoliceQueryLogMapper;

	@Resource
	private NciicInfoMapper nciicInfoMapper;

	@Resource
	private XpInfoMapper xpInfoMapper;

	@Resource
	private XpConfigurateMapper xpConfigurateMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveObject(SimplifyPoliceSingle simplifyPoliceSingle) {

		SimplifyPoliceQueryLog log = new SimplifyPoliceQueryLog();
		int result = 0;
		try {
			result = simplifyPoliceSingleMapper.insertObject(simplifyPoliceSingle);
		} catch (Exception e) {
			throw e;
		}
		if (result > 0) {
			// 记录查询日志
			log.setCertNo(simplifyPoliceSingle.getCertNo());
			log.setTrnId(simplifyPoliceSingle.getTrnId());
			log.setName(simplifyPoliceSingle.getName());
			log.setStaffCertNo(simplifyPoliceSingle.getStaffCertNo());
			log.setStaffName(simplifyPoliceSingle.getStaffName());
			log.setCrtUser(simplifyPoliceSingle.getCrtUser());

			// 单条实时查询
			log.setQueryMode("1");
			log.setIp(simplifyPoliceSingle.getIp());
		}
		return simPoliceQueryLogMapper.insertObject(log);
	}

	/*
	 * 批量实时查询提交批量信息
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveObject(String batchNo, MultipartFile[] files, User loginUser) throws Exception {
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 文件名
		String name = null;

		SimplifyPoliceBatchDetail detail = new SimplifyPoliceBatchDetail();
		SimplifyPoliceBatchFile fileDetail = new SimplifyPoliceBatchFile();
		// 记录文件的总行数
		int i = 0;
		// 记录所有文件的总行数
		int allLine = 0;
		try {
			// 查询证件类型
			for (MultipartFile file : files) {
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
						i++;
						String[] strArray = record.split("\\|");
						if (strArray.length != 2) {
							throw new ApplicationException("文件" + name + "第" + i + "行格式不对");
						}
						detail.setName(strArray[0]);
						detail.setCertNo(strArray[1]);
						detail.setQueryStatus("0");
						detail.setBatchFileId(fileId);
						detail.setCrtUser(loginUser.getAccount());
						simplifyPoliceBatchMapper.insertDetail(detail);
						record = br.readLine();
					}

					if (i > 2000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于2000");
					}
				} else {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());
					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount;
					if (i > 2000) {
						throw new ApplicationException("文件" + name + "导入数量不能大于2000");
					}
					// 遍历每一行 第一行为标题行
					for (int r = 0; r < rowCount; r++) {
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						if (cellCount != 2) {
							throw new ApplicationException("文件" + name + "第" + (r + 1) + "行超过最大列数2");
						}
						// 遍历每一列
						for (int c = 0; c < cellCount; c++) {
							Cell cell = row.getCell(c);// 获得列值
							if (cell != null) {
								switch (c) {
								case 0:
									detail.setName(cell.toString());
									break;
								case 1:
									detail.setCertNo(cell.toString());
								}
							}
						}
						detail.setQueryStatus("0");
						detail.setBatchFileId(fileId);
						detail.setCrtUser(loginUser.getAccount());
						simplifyPoliceBatchMapper.insertDetail(detail);
					}
				}
				if (i == 0) {
					throw new ApplicationException("文件" + name + "是空文件");
				}
				allLine += i;
				fileDetail.setBatchId(batchNo);
				fileDetail.setBatchFileName(name);
				fileDetail.setBatchFileRecordSize(String.valueOf(i));
				fileDetail.setCrtUser(loginUser.getAccount());
				fileDetail.setBatchFileId(fileId);
				simplifyPoliceBatchMapper.insertFileDetail(fileDetail);
			}
			SimplifyPoliceBatch batch = new SimplifyPoliceBatch();
			batch.setBatchId(batchNo);
			batch.setBatchRecordSize(String.valueOf(allLine));
			batch.setCrtUser(loginUser.getAccount());
			simplifyPoliceBatchMapper.insertBatch(batch);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				throw e;
			}
			throw new Exception("请求异常");
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return 1;
	}

	/* 批次查询--导入记录 分页总数 */
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return simplifyPoliceBatchMapper.selectBatchListCountByPage(args);
	}

	/* 批次查询--导入记录 分页记录 */
	@Override
	public List<NciicBatchReview> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return simplifyPoliceBatchMapper.selectBatchListByPage(args);
	}

	/* 批次查询--根据批次号查询该批次所属的文件 */
	@Override
	public List<SimplifyPoliceBatchFile> queryFileDetail(String batchId) {
		return simplifyPoliceBatchMapper.selectFileDetail(batchId);
	}

	/* 批次查询--根据该文件查询该文件所属的明细 */
	@Override
	public List<SimplifyPoliceBatchDetail> queryDetail(String batchFileId) {
		return simplifyPoliceBatchMapper.selectDetail(batchFileId);
	}

	/* 日志查询--日志分页总数 */
	@Override
	public int queryLogListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return simPoliceQueryLogMapper.selectLogListCountByPage(args);
	}

	/* 日志查询--日志分页 */
	@Override
	public List<SimplifyPoliceQueryLog> queryLogListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		return simPoliceQueryLogMapper.selectLogListByPage(args);
	}

	@Override
	public int updateStatusSingle(SimplifyPoliceSingle trn) {
		int result = simplifyPoliceSingleMapper.updateStatus(trn);
		if (result > 0) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("trnId", trn.getTrnId());
			args.put("queryResult", trn.getQueryStatus());
			result = simPoliceQueryLogMapper.updateQueryResult(args);
		}
		return result;
	}

	@Override
	public int queryHistoryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return nciicInfoMapper.selectHistoryListCountByPage(args);
	}

	public List<NciicInfo> queryHistoryListByPage(PageProperty page, User loginUser) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		List<NciicInfo> list = nciicInfoMapper.selectHistoryListByPage(args);
		// 记录查找日志
		SimplifyPoliceQueryLog log = new SimplifyPoliceQueryLog();
		if (list.size() > 0) {
			NciicInfo info = list.get(0);
			log.setCertNo(info.getGmsfhm());
			log.setName(info.getXm());
		} else {
			log.setCertNo(page.getDataMap().get("gmsfhm").toString());
		}
		log.setStaffCertNo(loginUser.getStaffId());
		log.setStaffName(loginUser.getStaffName());
		log.setCrtUser(loginUser.getAccount());
		log.setIp(loginUser.getIp());

		// 单条实时查找
		log.setQueryMode("2");
		simPoliceQueryLogMapper.insertObject(log);
		
		return list;
	}

	@Override
	public NciicInfo queryResult(String trnId) {
		return nciicInfoMapper.selectResult(trnId);
	}

	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return xpConfigurateMapper.selectCountByParams(args);
	}

	@Override
	public int queryXpHistoryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return xpInfoMapper.selectHistoryListCountByPage(args);
	}

	@Override
	public List<NciicXpInfo> queryXpHistoryListByPage(PageProperty page, User loginUser) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		List<NciicXpInfo> list = xpInfoMapper.selectHistoryListByPage(args);
		/*SimplifyPoliceQueryLog log = new SimplifyPoliceQueryLog();
		if (list.size() > 0) {
			NciicXpInfo info = list.get(0);
			log.setCertNo(info.getGmsfhm());
			log.setName(info.getXm());
		} else {
			log.setCertNo(page.getDataMap().get("identityCard").toString());
		}
		log.setStaffCertNo(loginUser.getStaffId());
		log.setStaffName(loginUser.getStaffName());
		log.setCrtUser(loginUser.getAccount());
		log.setIp(loginUser.getIp());
		
		// 单条实时查找
		log.setQueryMode("2");
		
		simPoliceQueryLogMapper.insertObject(log);*/
		
		return list;
	}
}
