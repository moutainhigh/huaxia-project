package com.huaxia.plaze.ui.unicom.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatch;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchDetail;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchFile;
import com.huaxia.plaze.ui.unicom.domain.PhoneHistory;
import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;
import com.huaxia.plaze.ui.unicom.domain.UnicomBatchInfo;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;
import com.huaxia.plaze.ui.unicom.mapper.PhoneBatchDetailMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneBatchFileMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneBatchMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneHistoryMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneQueryLogMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneQueryMapper;
import com.huaxia.plaze.ui.unicom.service.PhoneBatchService;
import com.huaxia.util.GuidUtil;

@Service("phoneBatchService")
public class PhoneBatchServiceImpl implements PhoneBatchService {

	private final static Logger logger = LoggerFactory.getLogger(PhoneBatchServiceImpl.class);

	@Resource
	private PhoneQueryMapper phoneQueryMapper;

	@Resource
	private PhoneQueryLogMapper phoneQueryLogMapper;

	@Resource
	private PhoneBatchDetailMapper phoneBatchDetailMapper;

	@Resource
	private PhoneBatchMapper phoneBatchMapper;

	@Resource
	private PhoneBatchFileMapper phoneBatchFileMapper;

	@Resource
	private PhoneHistoryMapper phoneHistoryMapper;

	@Resource
	private QueryConfigurateMapper queryConfigurateMapper;

	// 查询接口设置
	@Resource
	private QueryConfigurateMapper confQueryCountMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String saveBatch(String batchNo, MultipartFile[] files, User loginUser) throws ApplicationException {
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 文件名
		String name = null;

		List<PhoneBatchDetail> detailList = new ArrayList<>();
		List<PhoneBatchFile> fileList = new ArrayList<>();

		// 记录文件的总行数
		int i = 0;
		// 记录所有文件的总行数
		int allLine = 0;

		try {
			for (MultipartFile file : files) {
				// 获取一个文件的uuid
				String fileId = SequenceUtil.getUUID();
				i = 0;
				// 文件名
				name = file.getOriginalFilename();

				// 交易编号
				String trnId = GuidUtil.getGuid();
				if (name.indexOf("txt") != -1) {
					br = null;
					br = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
					record = br.readLine();
					while (StringUtils.isNotBlank(record)) {
						i++;
						String[] strArray = record.split("\\|");
						if (strArray.length != 3) {
							throw new ApplicationException("文件" + name + "第" + i + "行格式不对");
						}
						PhoneBatchDetail detail = new PhoneBatchDetail();
						detail.setTrnId(trnId);
						detail.setName(strArray[0]);
						detail.setCertNo(strArray[1]);
						detail.setMobile(strArray[2]);
						detail.setQueryStatus("0");
						detail.setBatchFileId(fileId);
						detail.setCrtUser(loginUser.getAccount());
						detailList.add(detail);
						record = br.readLine();

					}
					if (i > 2000) {
						logger.warn("文件{}导入数量不能大于2000条", name);
						return "文件" + name + "导入数量不能大于2000条";
					}
				} else {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());
					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount;
					if (i > 2000) {
						logger.warn("文件{}导入数量不能大于2000", name);
						return "文件" + name + "导入数量不能大于2000条";
					}
					// 遍历每一行 第一行为标题行
					for (int r = 0; r < rowCount; r++) {
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						if (cellCount != 3) {
							throw new ApplicationException("文件" + name + "第" + (r + 1) + "行超过最大列数3");
						}
						PhoneBatchDetail detail = new PhoneBatchDetail();
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
								case 2:
									detail.setMobile(cell.toString());
								}
							}
						}
						detail.setTrnId(trnId);
						detail.setQueryStatus("0");
						detail.setBatchFileId(fileId);
						detail.setCrtUser(loginUser.getAccount());
						detailList.add(detail);
					}
				}
				if (i == 0) {
					return "文件" + name + "是空文件";
				}
				allLine += i;
				PhoneBatchFile batchFile = new PhoneBatchFile();
				batchFile.setBatchId(batchNo);
				batchFile.setBatchFileName(name);
				batchFile.setBatchFileRecordSize(String.valueOf(i));
				batchFile.setCrtUser(loginUser.getAccount());
				batchFile.setBatchFileId(fileId);
				fileList.add(batchFile);
			}

			if (detailList != null && detailList.size() > 0) {
				for (PhoneBatchDetail detail : detailList) {
					phoneBatchDetailMapper.savePhoneTrnBatchDetail(detail);
				}
			}
			if (fileList != null && fileList.size() > 0) {
				for (PhoneBatchFile file : fileList) {
					phoneBatchFileMapper.savePhoneTrnBatchFile(file);
				}
			}

			PhoneBatch batch = new PhoneBatch();
			batch.setBatchId(batchNo);
			batch.setBatchRecordSize(String.valueOf(allLine));
			batch.setCrtUser(loginUser.getAccount());
			phoneBatchMapper.savePhoneTrnBatch(batch);

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
				return e.getMessage();
			}
			logger.error(e.getMessage(), e);
			return "提交失败";
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("资源关闭异常");
				}
			}
		}
		return "提交成功    生成批次号为" + batchNo;
	}

	/*
	 * 是否允许手机实名认证查询
	 * 
	 * @return 2:24小时数据有效性 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 */
	public String isAllowSingleQuery(String channelcode) {

		Map<String, Object> numMap = new HashMap<String, Object>();
		numMap.put("sourceCode", "001100");
		numMap.put("channelCode", channelcode);// 单条00 批量01

		String Msg = null;
		Date now = new Date();

		// 从数据库中查询设置的时间和数量
		QueryConfiguration queryCount = confQueryCountMapper.selectSettingByTaskType(numMap);
		if (queryCount != null) {
			Date startTime = queryCount.getStartTime();
			Date endTime = queryCount.getEndTime();
			if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
				Msg = "该时间段不能查询";
			} else {
				numMap.clear();
				numMap.put("source_code_num", "001100");
				numMap.put("channel_code_num", channelcode);

				// 调用存储过程进行验证，是否可以进行实时查询
				phoneQueryMapper.selectQueryResultBySource(numMap);
				int end_num = Integer.parseInt(String.valueOf(numMap.get("end_num")));
				if (end_num < 1) {
					logger.info("手机实名制实时查询 超出数量限制！");
					Msg = "该时间段内查询数量已达上限(包括现在所导入文件数量)";
				}
			}
		}
		return Msg;
	}

	/**
	 * 手机查询表批量查询数量限制 返回值 1:可以查询 返回值 -1:不在时间段内 返回值 -2:超出数量限制 返回值 0:查询异常
	 * 
	 */
	public int isAllowBatchQuery() {
		int result = 1;
		Map<String, Object> args = new HashMap<>();
		args.put("sourceCode", "001100");
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
			int count = phoneBatchDetailMapper.selectBatchCountByParams(args);
			if (count > queryTask.getMaxCount()) {
				logger.info("查询数量已达上限！");
				result = -2;
			}
		}
		return result;
	}

	@Override
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return phoneBatchMapper.selectBatchListCountByPage(args);
	}

	@Override
	public List<UnicomBatchInfo> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return phoneBatchMapper.selectBatchListByPage(args);
	}

	@Override
	public List<PhoneBatchDetail> selectDetail(String batchFileId) {
		return phoneBatchDetailMapper.selectDetail(batchFileId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveQueryLog(PhoneQueryLog phoneQueryLog) {
		return phoneQueryLogMapper.savePhoneQueryLog(phoneQueryLog);
	}

	@Override
	public int queryLogListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return phoneQueryLogMapper.selectLogListCountByPage(args);
	}

	@Override
	public List<PhoneQueryLog> queryLogListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		return phoneQueryLogMapper.selectLogListByPage(args);
	}

	@Override
	public List<PhoneBatchFile> queryFileDetail(String batchFileId) {
		return phoneBatchFileMapper.selectFileDetail(batchFileId);
	}

	@Override
	public int queryHistoryListCountByPage(String certNo) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("certNo", certNo);
		return phoneHistoryMapper.queryHistoryListCountByPage(args);
	}

	// @Override
	// public int queryHarHistoryListCountByPage(String certNo) {
	// Map<String, Object> args = new HashMap<String, Object>();
	// args.put("certNo", certNo);
	// return phoneHistoryMapper.queryHarHistoryListCountByPage(args);
	// }

	@Override
	public List<PhoneHistory> HistoryListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		List<PhoneHistory> list = phoneHistoryMapper.HistoryListByPage(args);
		// for (PhoneHistory pth : list) {
		// pth.setCompany("华道");
		// }
		return list;
	}

	// @Override
	// public List<PhoneHistory> harHistoryListByPage(PageProperty page) {
	// Map<String, Object> args = new HashMap<String, Object>();
	// args.put("pageNo", page.getPageNo());
	// args.put("pageSize", page.getPageSize());
	// args.putAll(page.getDataMap());
	// List<PhoneHistory> list = phoneHistoryMapper.harHistoryListByPage(args);
	// if (list.size() > 0) {
	// for (PhoneHistory pth : list) {
	// pth.setCompany("汇安融");
	// }
	//
	// }
	//
	// return list;
	// }

	@Override
	public UnicomPhoneData getResult(String trnId) {
		return phoneBatchDetailMapper.getResult(trnId);
	}

}
