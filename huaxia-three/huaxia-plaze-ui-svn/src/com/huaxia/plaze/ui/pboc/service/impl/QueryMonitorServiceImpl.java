package com.huaxia.plaze.ui.pboc.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.common.Account;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;
import com.huaxia.plaze.ui.pboc.mapper.SingleQueryMapper;
import com.huaxia.plaze.ui.pboc.mapper.SysLogQueryMapper;
import com.huaxia.plaze.ui.pboc.service.QueryMonitorService;
import com.huaxia.plaze.ui.pboc.support.QueryLogSupport;
import com.huaxia.plaze.ui.setting.domain.ConfigSetting;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.ConfigSettingMapper;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.system.domain.OperateLog;
import com.huaxia.plaze.ui.system.domain.Sms;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.mapper.OperateLogMapper;
import com.huaxia.plaze.ui.system.mapper.SmsMapper;
import com.huaxia.plaze.ui.system.mapper.UserMapper;
import com.huaxia.plaze.ui.unionpay.service.Interface3118Service;
import com.huaxia.util.CommonUtil;
import com.huaxia.util.SmsUtil;

//人行异常查询行为监控
@Service("queryMonitorService")
public class QueryMonitorServiceImpl implements QueryMonitorService {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryMonitorServiceImpl.class);

	@Resource
	private ConfigSettingMapper<ConfigSetting> configSettingMapper;

	@Resource
	private SysLogQueryMapper<SysLogQuery> sysLogQueryMapper;

	@Resource
	private UserMapper<User> userMapper;

	@Resource
	private SmsMapper<Sms> smsMapper;

	@Resource
	private Interface3118Service interface3118Service;

	@Resource
	private QueryLogSupport queryLogSupport;

	// 查询接口设置
	@Resource
	private QueryConfigurateMapper confQueryCountMapper;

	@Resource
	private SingleQueryMapper<SingleQueryReview> trnQueryReviewMapper;
	
	@Resource
	private OperateLogMapper operateLogMapper;

	public static final String messageId = AppConfig.getInstance().getValue("sms.prevent_notice");
	public static final String messageIdSameCert = AppConfig.getInstance().getValue("sms.notice_same_cert");

	/*
	 * 单条实时查询（提交复核）阻断设置
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String checkLimit(SingleQueryReview queryView, User loginUser, String queryExclude) throws Exception {
		String result = null;
		// 查询类型为贷后
		if ("01".equals(queryView.getQueryType())) {
			String certType = queryView.getCertType();
			String certNo = queryView.getCertNo();
			// 跟接口保持一致 证件类型需要转换
			// 身份证号
			if ("10".equals(certType)) {
				if (certNo.length() > 15) {
					certType = "01";
				} else {
					certType = "02";
				}
			}
			// 外国护照
			else if ("2".equals(certType)) {
				certType = "03";
			}
			// 军官证号
			else if ("20".equals(certType)) {
				certType = "05";
			}
			// 港澳台
			else if ("5".equals(certType)) {
				certType = "04";
			}
			// 其他
			else {
				certType = "20";
			}
			String sendData = certType + CommonUtil.formatStr(certNo, 18);
			// 根据证件号证件类型查询3118接口
			boolean hav = interface3118Service.saveData(sendData, " ", CommonUtil.checkLen(19));
			if (hav == false) {
				result = "被查询人无我行账户或账户状态异常，无法查询 ";
				queryLogSupport.queryAfterForbid(queryView, "0", "8", queryExclude, null, "被查询人无我行账户");
				return result;
			}

		}
		result = queryLimit(queryView.getCertNo(), "0", loginUser);
		if (queryView != null && loginUser != null) {
			logger.info("当前账户[{}]查询阻断校验：查询类型 -> {} | 查询限制结论 -> {}",
					new Object[] { loginUser.getAccount(), queryView.getQueryType(), result });
		}
		if (result != null) {
			String[] arr = result.split("!");
			if (arr.length == 3) {
				String message = arr[0];
				if (message.indexOf("下线") != -1) {
					queryLogSupport.queryAfterForbid(queryView, "0", "7", queryExclude, Integer.parseInt(arr[1]),
							arr[2]);
				} else {
					queryLogSupport.queryAfterForbid(queryView, "0", "8", queryExclude, Integer.parseInt(arr[1]),
							arr[2]);
				}
				return arr[0];
			}

		}
		return result;
	}

	/*
	 * 单条实时查找（历史数据查询）阻断设置
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String checkLimitSearch(String certNo, User loginUser, String queryExclude) throws Exception {

		String result = searchLimit(certNo, "1", loginUser);
		if (result != null) {
			String[] arr = result.split("!");
			if (arr.length == 3) {
				String message = arr[0];
				if (message.indexOf("下线") != -1) {
					queryLogSupport.queryAfterForbidSearch(certNo, "1", "7", queryExclude, Integer.parseInt(arr[1]),
							arr[2]);
				} else {
					queryLogSupport.queryAfterForbidSearch(certNo, "1", "8", queryExclude, Integer.parseInt(arr[1]),
							arr[2]);
				}
				return arr[0];
			}

		}

		return result;

	}

	// 单条实时查询
	private String queryLimit(String certNo, String queryFrom, User loginUser) throws IOException {
		int allowCount;
		int count;
		Map<String, Object> map = new HashMap<>();
		ConfigSetting setting = null;
		/* 30分钟内总查询条数大于**条 即禁止查询 操作用户列为禁用状态 */
		// 查询30分钟内可查询条数
		setting = configSettingMapper.selecMaxCountById("000101");
		allowCount = setting.getMaxCount();
		// 查询来源
		map.put("queryType", queryFrom);
		// 换算成分钟
		map.put("time", setting.getIntervalTime() + "/(24*60)");
		map.put("queryAction", "1");
		map.put("crtUser", loginUser.getAccount());
		// 查询30分钟之内已经查询的条数
		count = sysLogQueryMapper.selectCountByTime(map);
		if (loginUser != null) {
			logger.info("当前账户[{}]N分钟内查询限制信息：最大查询限制数量 -> {} | N分钟内已查询数量 -> {}",
					new Object[] { loginUser.getAccount(), allowCount, count });
		}
		// 如果已查询数量大于或等于可查询数量则账户禁用
		if (count >= allowCount) {
			// 记录系统限制日志
			doSaveOperateLog(loginUser.getAccount(), Account.OFF.getStatus());
			
			map.clear();
			map.put("id", loginUser.getUserId());
			map.put("status", "5");
			int result = userMapper.updateWithParamsById(map);
			if (result > 0) {
				return "禁止查询  您已被强制下线!" + count + "!" + setting.getIntervalTime() + "分钟内查询数量达到阈值";
			} else {
				return "提交失败";
			}
		}
		/* 当天累计查询>=三个月单日最大值 && 当天累计查询>=系统设置最大值 账户状态禁用 */
		// 三个月单日最大值（不包含今天）
		count = sysLogQueryMapper.selectMaxCountByMonth(map);
		// 当天累计查询值
		int todayCount = sysLogQueryMapper.selectCountByDay(map);
		if (loginUser != null) {
			logger.info("当前账户[{}]当天累计查询限制信息：最大查询限制数量 -> {} | 30分钟内已查询数量 -> {}",
					new Object[] { loginUser.getAccount(), allowCount, count });
		}
		if (todayCount >= count && todayCount >= loginUser.getPbocDayQueryMaxCount()) {
			// 记录系统限制日志
			doSaveOperateLog(loginUser.getAccount(), Account.OFF.getStatus());
			
			// 将当前"账户状态"修改为"禁用"
			map.clear();
			map.put("id", loginUser.getUserId());
			map.put("status", "5");
			int result = userMapper.updateWithParamsById(map);
			if (result > 0) {
				if (loginUser != null) {
					logger.warn("当前账户[{}]当天累计查询达到阈值被系统自动禁用：当天累计查询数量 -> {}",
							new Object[] { loginUser.getAccount(), todayCount });
				}
				return "禁止查询  您已被强制下线!" + todayCount + "!当天累计查询达到阈值";
			} else {
				return "提交失败";
			}
		}
		/* 查询同一个证件号 24小时内查询次数大于**条 即阻断查询 */
		// 拿到管理员的手机号
		User admin = userMapper.selectByUserAcct("admin");
		// 查询24小时允许多少条
		setting = configSettingMapper.selecMaxCountById("000103");
		allowCount = setting.getMaxCount();
		map.put("certNo", certNo);
		map.put("time", setting.getIntervalTime() + "/(24*60)");
		count = sysLogQueryMapper.selectCountByDayAndCert(map);
		if (count >= allowCount) {
			Sms sms = smsMapper.selectById(messageIdSameCert);
			SmsUtil.send(admin.getMobile(), loginUser.getAccount() + sms.getMsgText());
			if (loginUser != null) {
				logger.warn("当前账户[{}]相同证件号24小时查询数量达到阈值被系统自动禁用：相同证件号24小时查询数量 -> {}",
						new Object[] { loginUser.getAccount(), count });
			}
			return "禁止查询!" + count + "!相同证件号24小时查询数量达到阈值";
		}
		/* 查询用户三个月未进行查询人行实时查询 实时短信同时管理员 */
		map.clear();
		map.put("queryType", queryFrom);
		map.put("queryAction", "1");
		map.put("crtUser", loginUser.getAccount());
		count = sysLogQueryMapper.selectCountByMonth(map);
		if (count <= 0) {
			Sms sms = smsMapper.selectById(messageId);
			SmsUtil.send(admin.getMobile(), loginUser.getAccount() + sms.getMsgText());
			return null;
		}

		return null;
	}

	// 单条实时查找
	private String searchLimit(String certNo, String queryFrom, User loginUser) throws IOException {
		int allowCount;
		int count;
		Map<String, Object> map = new HashMap<>();
		ConfigSetting setting = null;
		/* 30分钟内总查询条数大于**条 即禁止查询 操作用户列为禁用状态 */
		// 查询30分钟内可查询条数
		setting = configSettingMapper.selecMaxCountById("000102");
		allowCount = setting.getMaxCount();
		// 查询来源
		map.put("queryType", queryFrom);
		// 换算成分钟
		map.put("time", setting.getIntervalTime() + "/(24*60)");
		map.put("queryAction", "6");
		map.put("crtUser", loginUser.getAccount());
		// 查询30分钟之内已经查询的条数
		count = sysLogQueryMapper.selectCountByTime(map);
		// 如何已查大于或等于可查 账户禁用
		if (count >= allowCount) {
			map.clear();
			map.put("id", loginUser.getUserId());
			map.put("status", "5");
			int result = userMapper.updateWithParamsById(map);
			if (result > 0) {
				return "禁止查询  您已被强制下线!" + count + "!" + setting.getIntervalTime() + "分钟内查找数量达到阈值";
			} else {
				return "提交失败";
			}
		}
		/* 当天累计查询>=三个月单日最大值 && 当天累计查询>=系统设置最大值 账户状态禁用 */
		// 三个月单日最大值（不好含今天）
		count = sysLogQueryMapper.selectMaxCountByMonth(map);
		// 当天累计查询值
		int todayCount = sysLogQueryMapper.selectCountByDay(map);
		if (todayCount >= count && todayCount >= loginUser.getPbocDaySearchMaxCount()) {
			map.clear();
			map.put("id", loginUser.getUserId());
			map.put("status", "5");
			int result = userMapper.updateWithParamsById(map);
			if (result > 0) {
				return "禁止查询  您已被强制下线!" + todayCount + "!当天累计查询达到阈值";
			} else {
				return "提交失败";
			}
		}
		/* 查询同一个证件号 24小时内查询次数大于**条 即阻断查询 */
		// 拿到管理员的手机号
		User admin = userMapper.selectByUserAcct("admin");
		// 查询24小时允许多少条
		setting = configSettingMapper.selecMaxCountById("000104");
		allowCount = setting.getMaxCount();
		map.put("certNo", certNo);
		map.put("time", setting.getIntervalTime() + "/(24*60)");
		count = sysLogQueryMapper.selectCountByDayAndCert(map);
		if (count >= allowCount) {
			Sms sms = smsMapper.selectById(messageIdSameCert);
			SmsUtil.send(admin.getMobile(), loginUser.getAccount() + sms.getMsgText());
			return "禁止查询!" + count + "!相同证件号24小时查询数量达到阈值";
		}
		/* 查询用户三个月未进行查询人行实时查询 实时短信同时管理员 */
		map.clear();
		map.put("queryType", queryFrom);
		map.put("queryAction", "6");
		map.put("crtUser", loginUser.getAccount());
		count = sysLogQueryMapper.selectCountByMonth(map);
		if (count <= 0) {
			Sms sms = smsMapper.selectById(messageId);
			SmsUtil.send(admin.getMobile(), loginUser.getAccount() + sms.getMsgText());
			return null;
		}

		return null;
	}

	public String queryInterfaceSetting(String queryFrom) {
		Map<String, Object> args = new HashMap<>();
		if ("0".equals(queryFrom)) {
			args.put("sourceCode", "000100");
			args.put("channelCode", "00");
			args.put("source", 0);
		} else {
			args.put("sourceCode", "000100");
			args.put("channelCode", "01");
			args.put("source", 2);
		}
		
		// TODO 从数据表CONF_QUERY_COUNT查询最大查询/找配置数量 
		QueryConfiguration queryTask = confQueryCountMapper.selectSettingByTaskType(args);
		if (queryTask != null) {
			Date date = new Date();
			if (date.getTime() < queryTask.getStartTime().getTime()
					|| date.getTime() > queryTask.getEndTime().getTime()) {
				return "该时间段不能查询";
			}
			// args.clear();
			args.put("startTime", queryTask.getStartTime());
			args.put("endTime", queryTask.getEndTime());
			args.put("queryFrom", queryFrom);
			int count = trnQueryReviewMapper.selectCountByReferTime(args);
			if ("0".equals(queryFrom)) {
				if (count >= queryTask.getMaxCount()) {
					return "该时间段内查询数量已达上限";
				}
			} else {
				if (count > queryTask.getMaxCount()) {
					return "该时间段内查询数量已达上限(包括现在所导入文件数量)";
				}
			}
		}
		return null;
	}

	// 记录系统阻断日志
	private void doSaveOperateLog(String account, String status) {
		if (StringUtils.isBlank(account)) {
			return;
		}

		User user = userMapper.selectByUserAcct(account);
		if (user != null) {
			OperateLog log = new OperateLog();
			log.setUserAcct(account);
			log.setUserName(user.getStaffName());
			log.setOperateAcct("999999");
			log.setOperateBefore(user.getStatus());
			log.setOperateAfter(status);
			operateLogMapper.insert(log);
		}
	}

}
