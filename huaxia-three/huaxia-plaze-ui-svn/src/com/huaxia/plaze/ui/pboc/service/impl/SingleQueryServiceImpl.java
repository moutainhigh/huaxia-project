package com.huaxia.plaze.ui.pboc.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
import com.huaxia.plaze.ui.pboc.domain.BatchQueryReview;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.PbocTaskCallStatus;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.mapper.BatchQueryMapper;
import com.huaxia.plaze.ui.pboc.mapper.FileStorageMapper;
import com.huaxia.plaze.ui.pboc.mapper.SingleQueryMapper;
import com.huaxia.plaze.ui.pboc.mapper.TaskCallStatusMapper;
import com.huaxia.plaze.ui.pboc.service.QueryMonitorService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.setting.domain.QueryExclude;
import com.huaxia.plaze.ui.setting.mapper.QueryExcludeMapper;
import com.huaxia.plaze.ui.system.domain.Dictionary;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.mapper.DictionaryMapper;
import com.huaxia.plaze.ui.system.mapper.HolidayMapper;
import com.huaxia.plaze.ui.system.mapper.UserMapper;

@Service("singleQueryService")
public class SingleQueryServiceImpl implements SingleQueryService {

	private final static Logger logger = LoggerFactory.getLogger(SingleQueryServiceImpl.class);

//	public static final String taskIp = AppConfig.getInstance().getValue("pboc.dispatch_target");

	@Resource
	private SingleQueryMapper<SingleQueryReview> singleQueryMapper;
	
	@Resource
	private DictionaryMapper<Dictionary> dictionaryMapper;
	
	@Resource
	private BatchQueryMapper<BatchQueryReview> batchQueryMapper;
	
	@Resource
	private TaskCallStatusMapper<PbocTaskCallStatus> taskCallStatusMapper;
	
	@Resource
	private UserMapper<User> userMapper;
	
	@Resource
	private FileStorageMapper<FileStorageInfo> fileStorageMapper;

	@Resource
	private QueryExcludeMapper<QueryExclude> queryExcludeMapper;

	@Resource
	private HolidayMapper holidayMapper;

	@Resource
	private QueryMonitorService queryMonitorService;

	/* 单条实时查询 --提交复核 */
	@Override
	public int saveObject(SingleQueryReview review) {
		return singleQueryMapper.insertObject(review);
	}

	/** 单条查询复核--分页总数量 */
	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return singleQueryMapper.selectListCountByPage(args);
	}

	/** 单条查询复核--分页总记录 */
	@Override
	public List<SingleQueryReview> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return singleQueryMapper.selectListByPage(args);
	}

	/* 单条查询复核--批量通过 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateBatchAgree(Map<String, Object> args) {
		String[] idArray = (String[]) args.get("idArray");
		Enumeration<NetworkInterface> nis;
		String ip=null;
		try{
			nis = NetworkInterface.getNetworkInterfaces();
			for(;nis.hasMoreElements();){
				NetworkInterface ni = nis.nextElement();
				Enumeration<InetAddress>  ias = ni.getInetAddresses(); 
				for(;ias.hasMoreElements();){
					InetAddress ia = ias.nextElement();
					if( ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1") ){
						ip = ia.getHostAddress();
					}
				}
			}
		}catch(Exception e){
			if(logger.isInfoEnabled()){
				  logger.info("SingleQueryService:{}",e);
			  }
		}
		for (String id : idArray) {
			SingleQueryReview queryView = singleQueryMapper.selectObjectById(id);
			PbocTaskCallStatus plaze = new PbocTaskCallStatus();
			// 唯一标识
			plaze.setUniqueFlag(queryView.getTrnId());
			plaze.setTaskIp(ip);
			// 证件号
			plaze.setIdentityNo(queryView.getCertNo());
			// 证件类型
			plaze.setIdentityType(queryView.getCertType());
			// 姓名
			plaze.setName(queryView.getName());
			// 最后操作人
			plaze.setLstOptUser((String) args.get("updUser"));
			//查询原因
			plaze.setQueryReason(queryView.getQueryType());
			// 执行插入
			taskCallStatusMapper.insertObject(plaze);
		}
		// 批量更新状态--正在查询
		return singleQueryMapper.updateBatchAgree(args);
	}

	/* 单条查询复核--批量退回 */
	@Override
	public int updateBatchReject(Map<String, Object> args) {
		return singleQueryMapper.updateBatchReject(args);
	}

	/* 单条查询复核--单个通过 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateSubmitAgree(Map<String, Object> args) {
		SingleQueryReview queryView = singleQueryMapper.selectObjectById((String) args.get("id"));
		PbocTaskCallStatus plaze = new PbocTaskCallStatus();
		Enumeration<NetworkInterface> nis;
		String ip=null;
		try{
			nis = NetworkInterface.getNetworkInterfaces();
			for(;nis.hasMoreElements();){
				NetworkInterface ni = nis.nextElement();
				Enumeration<InetAddress>  ias = ni.getInetAddresses(); 
				for(;ias.hasMoreElements();){
					InetAddress ia = ias.nextElement();
					if( ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1") ){
						ip = ia.getHostAddress();
					}
				}
			}
		}catch(Exception e){
			if(logger.isInfoEnabled()){
				  logger.info("SingleQueryService:{}",e);
			  }
		}
		// 唯一标识
		plaze.setUniqueFlag(queryView.getTrnId());
		plaze.setTaskIp(ip);
		// 证件号
		plaze.setIdentityNo(queryView.getCertNo());
		// 证件类型
		plaze.setIdentityType(queryView.getCertType());
		// 姓名
		plaze.setName(queryView.getName());
		// 最后操作人
		plaze.setLstOptUser((String) args.get("updUser"));
		//查询原因
		plaze.setQueryReason(queryView.getQueryType());
		// 执行插入
		taskCallStatusMapper.insertObject(plaze);
		// 批量更新状态--正在查询
		return singleQueryMapper.updateSubmitAgree(args);
	}

	/* 单条查询复核--单个退回 */
	@Override
	public int updateSubmitReject(Map<String, Object> args) {

		return singleQueryMapper.updateSubmitReject(args);
	}

	/* 批量实时查询--提交复核 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveObjectList(SingleQueryReview queryReview, MultipartFile[] files, String batchNo) throws Exception {
		// 定义文件输入流
		BufferedReader br = null;
		// 读取txt文件每一行记录
		String record = null;
		// 证件类型
		String certType = null;
		// 文件名
		String name = null;
		// 记录文件的总行数
		int i = 0;
		try {
			// 查询证件类型
			List<Dictionary> codeList = (List<Dictionary>) dictionaryMapper.selectListByDictCode("CERT_TYPE");
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
						if (strArray.length != 3) {
							throw new ApplicationException("文件" + name + "第" + i + "行格式不对");
						}
						// 检验第一位证件号格式
						if (!checkIdCard(strArray[0])) {
							throw new ApplicationException("文件" + name + "第" + i + "行证件号格式错误");
						}
						certType = strArray[1];
						// 检验第二位证件类别
						if (!checkCertType(codeList, certType)) {
							throw new ApplicationException("文件" + name + "第" + i + "行证件类型代码不存在");
						}
						// 检验第三位姓名
						if (!StringUtils.isNotBlank(strArray[2])) {
							throw new ApplicationException("文件" + name + "第" + i + "行姓名长度不能为空");
						}
						// 检验姓名长度
						if (strArray[2].length() > 32) {
							throw new ApplicationException("文件" + name + "第" + i + "行姓名长度不能超过32位");
						}
						queryReview.setCertNo(strArray[0]);
						queryReview.setCertType(String.valueOf(certType));
						queryReview.setName(strArray[2]);
						queryReview.setFileId(fileId);
						singleQueryMapper.insertObject(queryReview);
						record = br.readLine();

					}

				} else {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					Sheet sheet = workbook.getSheetAt(0);
					// 获取总行数
					int rowCount = sheet.getPhysicalNumberOfRows();
					i = rowCount;
					String noType;
					// 遍历每一行 第一行为标题行
					for (int r = 0; r < rowCount; r++) {
						Row row = sheet.getRow(r);
						int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
						// 遍历每一列
						for (int c = 0; c < cellCount; c++) {
							Cell cell = row.getCell(c);// 获得列值
							if (cell != null) {
								switch (c) {
								case 0: // 证件号
									// 检验第一位证件号格式
									if (!checkIdCard(cell.toString())) {
										throw new ApplicationException("文件" + name + "第" + (r + 1) + "行证件号格式错误");
									}
									queryReview.setCertNo(cell.toString());
									break;
								case 1:
									DecimalFormat df = new DecimalFormat("#");
									// 代表字符串
									if (cell.getCellType() == 1) {
										noType = cell.toString();
									} else {
										// 数字 舍去后缀0
										noType = df.format(cell.getNumericCellValue()).toString();
									}
									// 检验第二位证件类别
									if (!checkCertType(codeList, noType)) {
										throw new ApplicationException("文件" + name + "第" + (r + 1) + "行证件类型代码不存在");
									}

									queryReview.setCertType(String.valueOf(noType));
									break;
								case 2:
									// 检验第三位姓名
									if (!StringUtils.isNotBlank(cell.toString())) {
										throw new ApplicationException("文件" + name + "第" + (r + 1) + "行姓名长度不能为空");
									}
									// 检验第一位证件号格式
									if (cell.toString().length() > 32) {
										throw new ApplicationException("文件" + name + "第" + (r + 1) + "行姓名长度不能超过32位");
									}
									queryReview.setName(cell.toString());
									break;

								}
							}
						}
						queryReview.setFileId(fileId);
						singleQueryMapper.insertObject(queryReview);
					}
				}
				if (i == 0) {
					throw new ApplicationException("文件" + name + "是空文件");
				}
				BatchQueryReview batch = new BatchQueryReview();
				batch.setBatchId(batchNo);
				batch.setFileName(name);
				batch.setTotalRecord(String.valueOf(i));
				batch.setCrtUser(queryReview.getCrtUser());
				batch.setFileId(fileId);
				batch.setReviewStatus("0");
				batch.setQueryType(queryReview.getQueryType());
				batchQueryMapper.insertObject(batch);
			}
			// 查询接口设置
			String resultInterface = queryMonitorService.queryInterfaceSetting("1");
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
				br.close();
			}
		}

		return 1;
	}

	// 校验证件类型
	private boolean checkCertType(List<Dictionary> codeList, String certCode) {
		for (Dictionary code : codeList) {
			if (code.getDictCode().equals(certCode)) {
				return true;
			}
		}
		return false;
	}

	/* 根据ID查明细 */
	@Override
	public SingleQueryReview queryObjectById(String id) {
		return singleQueryMapper.selectObjectById(id);
	}

	/* 根据ID更新单条记录 */
	@Override
	public int updateObject(SingleQueryReview queryReview) {
		return singleQueryMapper.updateObject(queryReview);
	}

	/* 查人行询在区间范围时间查询的总数 */
	@Override
	public int queryAllowCount(Map<String, Object> args) {
		return singleQueryMapper.selectAllowCount(args);
	}

	/* 提交查询是验证查询设置 */
	@Override
	public String checkAuth(String userId) {
		Date startTime = null;
		Date endTime = null;
		Date now = new Date();
		Date nowDate = null;
		String today = null;
		User user = null;
		Map<String, Object> args = new HashMap<String, Object>();
		try {
			// 首先验证例外情况
			user = userMapper.selectById(userId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			today = df.format(now);
			args.put("userAcct", user.getAccount());
			args.put("excludeDate", today);
			
			// 查询例外数据 [ LISTAGG ]
			String exceludeTime = queryExcludeMapper.selectExclude(args);
			if (user != null) {
				logger.info("当前账户[{}]人行查询例外时间段：{}", new Object[] { user.getAccount(), exceludeTime });
			}
			df = new SimpleDateFormat("HH:mm");
			if (exceludeTime != null) {
				// 检测当前时间点是否处于例外查询时间范围内
				String[] timeArray = exceludeTime.split(",");
				for (int i = 0; i < timeArray.length; i++) {
					String[] time = timeArray[i].split("-");
					startTime = df.parse(time[0]);
					endTime = df.parse(time[1]);
					nowDate = df.parse(df.format(now));
					// 返回空说明满足例外情况可以查询
					if (nowDate.getTime() >= startTime.getTime() && nowDate.getTime() <= endTime.getTime()) {
						return "例外";
					}
				}
			}
			args.clear();
			if ("0".equals(user.getPbocAuth())) {
				if (user != null) {
					logger.warn("当前账户[{}]人行查询操作因未被管理员授权被系统自动拒绝：{}", new Object[] { user.getAccount(), user.getPbocAuth() });
				}
				return "没有人行查询权限";
			}
			// 校验节假日查询限制
			int count = holidayMapper.selectBooleanHoliday(today);
			if (count > 0) {
				if (user != null) {
					logger.warn("当前账户[{}]人行查询操作因节假日被系统自动拒绝：{}", new Object[] { user.getAccount(), count });
				}
				return "当前日期是节假日";
			}

			// 查询个人的查询权限 查询时间段如9.00-17.00
			String[] queryTime = user.getPbocAuthQueryTime().split("-");
			df = new SimpleDateFormat("HH:mm");
			startTime = df.parse(queryTime[0]);
			endTime = df.parse(queryTime[1]);
			nowDate = df.parse(df.format(now));
			if (nowDate.getTime() < startTime.getTime() || nowDate.getTime() > endTime.getTime()) {
				return "该时间段不允许人行查询退回和修改";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "请求异常";
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveObject(List<FileStorageInfo> infoList, SingleQueryReview queryReview) {
		int result = 0;
		for (FileStorageInfo info : infoList) {
			result = fileStorageMapper.insert(info);
		}
		if (result > 0) {
			if (queryReview.getTrnId() != null) {
				result = singleQueryMapper.updateObject(queryReview);
			} else {
				result = singleQueryMapper.insertObject(queryReview);
			}
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int saveObjectBatch(List<FileStorageInfo> infoList, SingleQueryReview queryReview, MultipartFile[] files,
			String batchNo) throws Exception {
		int result = 0;
		for (FileStorageInfo info : infoList) {
			result = fileStorageMapper.insert(info);
		}
		if (result > 0) {
			try{
				result = saveObjectList(queryReview, files, batchNo);
			}catch(Exception e){
			 throw e;
			}
			
		}
		return result;
	}

	/**
	 * 检查被提交数据的用户状态是否被禁用
	 */
	@Override
	public int checkUser(String ids) {
		Map<String, Object> map = new HashMap<>();
		String[] idArray = ids.split(",");
		map.put("idArray", idArray);
		map.put("status", "5");
		return singleQueryMapper.selectUserStatusByTrnId(map);
	}

	public static boolean checkIdCard(String idCard) {
		if (idCard.length() == 0 || idCard.length() > 20) {
			return false;
		}
		return true;

	}

}
