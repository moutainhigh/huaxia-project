package com.huaxia.plaze.ui.id5.service.impl;

import java.io.BufferedReader;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.id5.domain.Education;
import com.huaxia.plaze.ui.id5.domain.EducationBatch;
import com.huaxia.plaze.ui.id5.domain.EducationBatchDetail;
import com.huaxia.plaze.ui.id5.domain.EducationBatchFile;
import com.huaxia.plaze.ui.id5.domain.EducationQueryLog;
import com.huaxia.plaze.ui.id5.domain.EducationSingle;
import com.huaxia.plaze.ui.id5.mapper.EducationBatchMapper;
import com.huaxia.plaze.ui.id5.mapper.EducationConfigurate;
import com.huaxia.plaze.ui.id5.mapper.EducationMapper;
import com.huaxia.plaze.ui.id5.mapper.EducationQueryLogMapper;
import com.huaxia.plaze.ui.id5.mapper.EducationSingleMapper;
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.system.domain.User;

@Service("educationService")
public class EducationServiceImpl implements EducationService {

	private final static Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);
	@Resource
	private EducationSingleMapper educationSingleMapper;
	@Resource
	private EducationBatchMapper educationBatchMapper;

	@Resource
	private EducationQueryLogMapper educationQueryLogMapper;

	@Resource
	private EducationMapper educationMapper;
	@Resource
	private EducationConfigurate educationConfigurate;
	// 查询接口设置
	@Resource
	private QueryConfigurateMapper confQueryCountMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	/**
	 * 
	 * //返回值 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值 2:24小时数据有效性
	 */
	public int save(EducationSingle educationSingle) {
		int result = 0;
		EducationQueryLog log = new EducationQueryLog();
		// 24小时数据有效性校验
		Map<String, Object> argsCertNo = new HashMap<String, Object>();
		argsCertNo.put("certNo", educationSingle.getCertNo());
		result = query24HoursCountByParams(argsCertNo);
		if (result > 0) {
			// 需要记录一条日志
			log.setCertNo(educationSingle.getCertNo());
			log.setStaffCertNo(educationSingle.getStaffCertNo());
			log.setTrnId(educationSingle.getTrnId());
			log.setName(educationSingle.getName());
			log.setStaffName(educationSingle.getStaffName());
			log.setCrtUser(educationSingle.getCrtUser());
			// 设置查询模式到单条实时查找
			log.setQueryMode("1");
			log.setIp(educationSingle.getIp());
			educationQueryLogMapper.insertObject(log);
			return 2;
		}
		int isAllow = isAllowSingleQuery();
		if (isAllow != 1) {
			return isAllow;
		} else {
			result = educationSingleMapper.insert(educationSingle);
		}

		if (result > 0) {

			// 需要记录一条日志
			log.setCertNo(educationSingle.getCertNo());
			log.setTrnId(educationSingle.getTrnId());
			log.setName(educationSingle.getName());
			log.setStaffCertNo(educationSingle.getStaffCertNo());
			log.setStaffName(educationSingle.getStaffName());
			log.setCrtUser(educationSingle.getCrtUser());
			// 设置查询模式到单条实时查询
			log.setQueryMode("1");
			log.setIp(educationSingle.getIp());
		}
		return educationQueryLogMapper.insertObject(log);

	}

	/*
	 * 批量实时查询提交批量信息 返回值１：可以查询 返回值- 1：不在时间段内 返回值-2：超出数量限制 返回值 0:失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveBatch(String batchNo, MultipartFile[] files, User loginUser) throws Exception {
		/*
		 * Map<String, Object> args = new HashMap<>(); args.put("sourceCode",
		 * "000300"); args.put("channelCode", "01"); QueryConfiguration
		 * queryTask = confQueryCountMapper.selectSettingByTaskType(args); if
		 * (queryTask == null) { return -3; }
		 */
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 文件名
		String name = null;

		EducationBatchDetail detail = new EducationBatchDetail();
		EducationBatchFile fileDetail = new EducationBatchFile();
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
						educationBatchMapper.insertDetail(detail);
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
						educationBatchMapper.insertDetail(detail);
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
				educationBatchMapper.insertFileDetail(fileDetail);
			}
			EducationBatch batch = new EducationBatch();
			batch.setBatchId(batchNo);
			batch.setBatchRecordSize(String.valueOf(allLine));
			batch.setCrtUser(loginUser.getAccount());
			educationBatchMapper.insertBatch(batch);

			int isAllow = isAllowBatchQuery();
			if (isAllow != 1) {
				if (isAllow == -1) {
					throw new ApplicationException("不在可查询时间段内！");
				} else if (isAllow == -2) {
					throw new ApplicationException("查询数量已达上限！");
				}
			}

		} catch (Exception e) {
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

	/**
	 * 学历查询表数量限制 返回值 1:可以查询 返回值 -1:不在时间段内 返回值 -2:超出数量限制 返回值 0:查询异常
	 * 
	 */
	public int isAllowSingleQuery() {
		int result = 1;
		Map<String, Object> args = new HashMap<>();
		// 00 单条查询
		args.put("sourceCode", "000300");
		args.put("channelCode", "00");

		QueryConfiguration queryTask = confQueryCountMapper.selectSettingByTaskType(args);
		if (queryTask != null) {
			Date date = new Date();
			if (date.getTime() < queryTask.getStartTime().getTime()
					|| date.getTime() > queryTask.getEndTime().getTime()) {

				result = -1;
			} else {
				args.clear();
				args.put("source_code_num", "000300");
				args.put("channel_code_num", "00");

				// 调用存储过程进行验证，是否可以进行实时查询
				try {
					educationMapper.selectQueryResultBySource(args);
					int end_num = Integer.parseInt(args.get("end_num").toString());
					if (end_num >= 1) {
						logger.info("学历实时查询 可以进行查询！");
						result = 1;
					} else {
						logger.info("学历实时查询 超出数量限制！");
						result = -2;
					}
				} catch (Exception e) {
					logger.error("学历实时查询 从数据库调用查询数量方法：调用存储过程:datasource_id5_count,异常！");
					result = 0;
				}

			}
		}
		return result;
	}

	/**
	 * 学历查询表批量查询数量限制 返回值 1:可以查询 返回值 -1:不在时间段内 返回值 -2:超出数量限制 返回值 0:查询异常
	 * 
	 */
	public int isAllowBatchQuery() {
		int result = 1;
		Map<String, Object> args = new HashMap<>();
		args.put("sourceCode", "000300");
		args.put("channelCode", "01");
		QueryConfiguration queryTask = confQueryCountMapper.selectSettingByTaskType(args);
		if (queryTask != null) {
			Date date = new Date();
			if (date.getTime() < queryTask.getStartTime().getTime()
					|| date.getTime() > queryTask.getEndTime().getTime()) {

				result = -1;
			}

			args.put("startTime", queryTask.getStartTime());
			args.put("endTime", queryTask.getEndTime());
			// 进行数量查找
			int count = educationBatchMapper.selectBatchCountByParams(args);
			if (count > queryTask.getMaxCount()) {
				logger.info("查询数量已达上限！");
				result = -2;
			}
		}
		return result;
	}

	/* 批次查询--导入记录 分页总数 */
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return educationBatchMapper.selectBatchListCountByPage(args);
	}

	/* 批次查询--导入记录 分页记录 */
	@Override
	public List<EducationBatch> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return educationBatchMapper.selectBatchListByPage(args);
	}

	/* 批次查询--根据批次号查询该批次所属的文件 */
	@Override
	public List<EducationBatchFile> queryFileDetail(String batchId) {
		return educationBatchMapper.selectFileDetail(batchId);
	}

	/* 批次查询--根据该文件查询该文件所属的明细 */
	@Override
	public List<EducationBatchDetail> queryDetail(String batchFileId) {
		return educationBatchMapper.selectDetail(batchFileId);
	}

	/* 日志查询--日志分页总数 */
	@Override
	public int queryLogListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return educationQueryLogMapper.selectLogListCountByPage(args);
	}

	/* 日志查询--日志分页 */
	@Override
	public List<EducationQueryLog> queryLogListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		return educationQueryLogMapper.selectLogListByPage(args);
	}

	@Override
	public int updateStatusSingle(EducationSingle educationSingle) {
		Map<String, Object> args = new HashMap<String, Object>();

		// 单条参数
		args.put("id", educationSingle.getTrnId());
		args.put("status", educationSingle.getQueryStatus());

		// 日志参数
		args.put("trnId", educationSingle.getTrnId());
		args.put("queryResult", educationSingle.getQueryStatus());
		int result = educationSingleMapper.updateStatusById(args);
		if (result > 0) {
			result = educationQueryLogMapper.updateQueryResult(args);
		}
		return result;

	}

	@Override
	public int queryHistoryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return educationMapper.selectHistoryListCountByPage(args);
	}

	@Override
	public List<Education> queryHistoryListByPage(PageProperty page, User loginUser) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		List<Education> list = educationMapper.selectHistoryListByPage(args);
		EducationQueryLog log = new EducationQueryLog();
		if (list.size() > 0) {
			Education education = list.get(0);
			// 需要记录一条日志
			log.setCertNo(education.getIdentityCard());
			log.setName(education.getUserName());
		} else {
			log.setCertNo(page.getDataMap().get("identityCard").toString());
		}
		log.setStaffCertNo(loginUser.getStaffId());
		log.setStaffName(loginUser.getStaffName());
		log.setCrtUser(loginUser.getAccount());
		log.setIp(loginUser.getIp());
		// 设置查询模式到单条实时查找
		log.setQueryMode("2");
		/*
		 * String mac = HardwareUtil.checkSys(loginUser.getIp());
		 * log.setMac(mac);
		 */
		educationQueryLogMapper.insertObject(log);
		return list;
	}

	@Override
	public Education queryResultByTrnId(String trnId) {
		return educationMapper.selectResultByTrnId(trnId);
	}

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> params) {
		return educationConfigurate.selectCountByTime(params);
	}

	@Override
	public int updateStatusById(String trnId, String queryStatus) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", trnId);
		args.put("status", queryStatus);
		return educationSingleMapper.updateStatusById(args);
	}

	@Override
	public int query24HoursCountByParams(Map<String, Object> args) {
		return educationSingleMapper.query24HoursCountByParams(args);
	}

	@Override
	public String query24HoursDataTrnIDByParams(Map<String, Object> args) {
		return educationSingleMapper.query24HoursDataTrnIDByParams(args);
	}
}
