package com.huaxia.plaze.ui.system.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.cache.CacheSupport;
import com.huaxia.plaze.common.Account;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.setting.domain.ConfigSetting;
import com.huaxia.plaze.ui.setting.service.ConfigSettingService;
import com.huaxia.plaze.ui.system.common.Authorization;
import com.huaxia.plaze.ui.system.common.SystemSettings;
import com.huaxia.plaze.ui.system.domain.OperateLog;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.OperateLogService;
import com.huaxia.plaze.ui.system.service.SmsService;
import com.huaxia.plaze.ui.system.service.UserRoleService;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;
import com.huaxia.util.ServerUtil;
import com.huaxia.util.SmsUtil;

/**
 * 授权验证控制器
 * 
 * @author zhiguo.li 初始版本
 * @version 1.0.2
 * @modify zhiguo.li 2018-12-21 登录技术方案变更
 * @modify zhiguo.li 2019-09-24 登录技术方案优化
 *
 */
@Controller
public class AuthorizationController {

	private final static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	/** 用户登录缓存前缀 */
	private static String USER_CACHE_PREFIX = Cache.NAMESPACE + ":USER:";

	@Resource
	private UserService userService;

	@Resource
	private SmsService smsService;

	@Resource
	private UserRoleService userRoleService;

	@Resource
	private ConfigSettingService configSettingService;
	
	@Resource
	private OperateLogService operateLogService;

	/** 获取手机短信验证码 */
	@ResponseBody
	@RequestMapping("verify")
	public Object getValidCode(HttpServletRequest request, String account)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();

		// 请求参数校验
		if (StringUtils.isBlank(account)) {
			response.put("result", false);
			return new ObjectMapper().writeValueAsString(response);
		}

		User user = userService.queryByAccount(account);
		if (user != null) {
			String mobile = user.getMobile();
			if (StringUtils.isNotBlank(mobile)) {
				String captcha = CacheUtil.get(USER_CACHE_PREFIX + account + ":CAPTCHA");
				if (StringUtils.isBlank(captcha)) {
					captcha = captcha();
				}

				// 页面输出"短信验证码"
				response.put("success", true);
				response.put("mobile", mobile);
				response.put("message", captcha);

				// 发送短信
				String template = smsService.queryById(appConfig.getValue("sms.captcha")).getMsgText();
				String message = String.format(template, captcha);
				logger.info("请求短信平台向手机号 [ {} ] 发送短信内容 [ {} ]", new Object[] { mobile, message });
				try {
					SmsUtil.send(mobile, message);
				} catch (Exception e) {
					logger.error("发送短信[{}]至用户[{}]手机[{}]失败", new Object[] { message, user.getAccount(), mobile });
				}

				// 短信验证码加载至缓存
				CacheUtil.set(USER_CACHE_PREFIX + account + ":CAPTCHA", captcha, CacheSupport.CAPTCHA_TIMEOUT,
						TimeUnit.SECONDS);
			} else {
				response.put("success", false);
				response.put("mobile", null);
				response.put("message", "手机号码为空");
			}
		} else {
			response.put("success", false);
			response.put("message", "账户不存在");
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 登入系统 */
	@ResponseBody
	@RequestMapping("login")
	public Object login(HttpServletRequest request, String account, String password, String validCode)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		Map<String, Object> response = new HashMap<String, Object>();

		// --------------------------
		// -- [校验] 请求参数验证
		// --------------------------
		response = validateRequestParams(account, password, validCode);
		if (!Boolean.parseBoolean(String.valueOf(response.get("result")))) {
			logger.warn("请求参数校验失败 [ {} ]", response.get("message"));
			return new ObjectMapper().writeValueAsString(response);
		}

		// --------------------------
		// -- [校验] 短信验证码验证
		// --------------------------
		response = validateCaptcha(account, validCode);
		if (!Boolean.parseBoolean(String.valueOf(response.get("result")))) {
			logger.warn("短信验证码校验失败 [ {} ]", response.get("message"));

			// 如果短信验证码错误次数达到系统允许上限，则锁定当前用户。
			if (Authorization.SMS_CAPTCHA_ERROR_REACHED_MAX_LIMIT.getCode().equals(response.get("code"))) {
				// 记录系统限制日志
				doSaveOperateLog(account, Account.LOCK.getStatus());
				
				setAccountStatus(account, Account.LOCK.getStatus());
				logger.warn("账户[ {} ] 因 [ {} ] 原因被 [ {} ]", new Object[] { account,
						Authorization.SMS_CAPTCHA_ERROR_REACHED_MAX_LIMIT.getDesc(), Account.LOCK.getDescription() });
			}
			// 如果短信验证码错误次数未达上限，则错误次数++。
			else {
				String allowErrorCount = String.valueOf(response.get("allow_error_count"));
				if (StringUtils.isNotBlank(allowErrorCount)) {
					CacheUtil.set(USER_CACHE_PREFIX + account + ":CAPTCHA:ALLOW_ERROR_COUNT", allowErrorCount,
							CacheSupport.SESSION_TIMEOUT, TimeUnit.SECONDS);
				}
			}
			return new ObjectMapper().writeValueAsString(response);
		}

		// --------------------------
		// -- [校验] 登录账号验证
		// --------------------------
		User user = null;
		String userCacheInfo = CacheUtil.get(USER_CACHE_PREFIX + account);
		logger.info("应用缓存中账号 [ {} ] 对应用户 [ {} ]",
				new Object[] { account, (StringUtils.isBlank(userCacheInfo) ? "不存在" : "存在") });
		if (StringUtils.isNotBlank(userCacheInfo)) {
			user = JSONObject.parseObject(userCacheInfo, User.class);
			if (user != null && user.getAccount().equals(account)
					&& user.getPassword().equals(password.toUpperCase())) {
				// 刷新缓存信息（删除旧的增加新的）
				String jSessionId = CacheUtil.get(USER_CACHE_PREFIX + account + ":JSESSIONID");
				CacheUtil.del(USER_CACHE_PREFIX + jSessionId);
				jSessionId = request.getRequestedSessionId();
				CacheUtil.set(USER_CACHE_PREFIX + jSessionId, account);
				CacheUtil.set(USER_CACHE_PREFIX + account + ":JSESSIONID", jSessionId);

				// 更新用户登录信息（最后登录时间）  无USER_ID,更新为0
				user.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				userService.updateByUser(user);
				
				response = build(true, Authorization.LOGIN_SUCCESS.getCode(), Authorization.LOGIN_SUCCESS.getDesc());
				return new ObjectMapper().writeValueAsString(response);
			}
		}
		if (user == null) {
			user = userService.queryByAccount(account);
			if (user == null) {
				String allowPasswordErrorCount = CacheUtil
						.get(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT");
				if (StringUtils.isBlank(allowPasswordErrorCount)) {
					CacheUtil.set(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT", "1",
							CacheSupport.SESSION_TIMEOUT, TimeUnit.SECONDS);
				}

				response = build(false, Authorization.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
						Authorization.ACCOUNT_OR_PASSWORD_ERROR.getDesc());
				return new ObjectMapper().writeValueAsString(response);
			} 
		}
		//-------------
		//--[校验] 用户状态 非开通状态的用户不可登录
		//-------------
		if(!"1".equals(user.getStatus())){
			response = build(false,"","账号状态异常,请联系管理员！");
			return new ObjectMapper().writeValueAsString(response);
		}
		
		// --------------------------
		// -- [校验] 客户端授权IP验证
		// --------------------------
		response = validateTerminalAddress(request, user);
		if (!Boolean.parseBoolean(String.valueOf(response.get("result")))) {
			return new ObjectMapper().writeValueAsString(response);
		}

		// --------------------------
		// -- [校验] 登录认证验证
		// --------------------------
		if (checked(user, account, password)) {
			// 首次登录系统验证
			if (user.isFirstTimeLogin()) {
				response = build(true, Authorization.FIRST_LOGIN_SYSTEM.getCode(),
						Authorization.FIRST_LOGIN_SYSTEM.getDesc());
				response.put("target", "change");
				return new ObjectMapper().writeValueAsString(response);
			}

			// 登录密码过期验证
			if (StringUtils.isNotBlank(user.getPasswordExpireTime())) {
				Date expireTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user.getPasswordExpireTime());
				if (new Date().after(expireTime)) {
					response = build(true, Authorization.LOGIN_PASSWORD_EXPIRED.getCode(),
							Authorization.LOGIN_PASSWORD_EXPIRED.getDesc());
					response.put("target", "change");
					return new ObjectMapper().writeValueAsString(response);
				}
			}

			// 单位时间登录次数验证
			ConfigSetting setting = configSettingService
					.querySettingById(SystemSettings.LOGIN_SYSTEM_MAX_COUNT.getCode());
			if (setting != null) {
				int maxCount = setting.getMaxCount();
				int intervalTime = setting.getIntervalTime();
				String cacheLoginCount = CacheUtil.get(USER_CACHE_PREFIX + account + ":LOGIN_COUNT");
				if (StringUtils.isBlank(cacheLoginCount)) {
					CacheUtil.set(USER_CACHE_PREFIX + account + ":LOGIN_COUNT", "1", intervalTime, TimeUnit.MINUTES);
				} else {
					int loginCount = Integer.parseInt(cacheLoginCount) + 1;
					if (loginCount <= maxCount) {
						CacheUtil.set(USER_CACHE_PREFIX + account + ":LOGIN_COUNT", String.valueOf(loginCount),
								intervalTime, TimeUnit.MINUTES);
					} else {
						sendSmsToAdmin(account);
					}
				}
			}

			// 长时间未登录验证（30天）
			if (StringUtils.isNotBlank(user.getLastLoginTime())) {
				Date lastLoginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user.getLastLoginTime());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(lastLoginTime);
				calendar.add(Calendar.DAY_OF_YEAR, 30);
				Date time = calendar.getTime();
				if (new Date().after(time)) {
					// 记录系统限制日志
					doSaveOperateLog(account, Account.LOCK.getStatus());
					
					// 长时间未登录系统, 锁定当前账号并清除缓存 .
					setAccountStatus(user.getAccount(), Account.LOCK.getStatus());
					String jSessionId = CacheUtil.get(USER_CACHE_PREFIX + account + ":JSESSIONID");
					CacheUtil.del(USER_CACHE_PREFIX + jSessionId);
					
					response = build(false, Authorization.LONG_TIME_UNLOGIN.getCode(),
							Authorization.LONG_TIME_UNLOGIN.getDesc());
					return new ObjectMapper().writeValueAsString(response);
				}
			}

			// 更新用户相关信息
			user.addPasswordExpireTime(30);
			user.setFirstLogin("0");
			user.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			userService.updateByUser(user);
			if (StringUtils.isBlank(user.getIp())) {
				user.setIp(ServerUtil.getRemoteAddr(request));
			}

			// 刷新应用缓存
			flushCache(request, user);

			response = build(true, Authorization.LOGIN_SUCCESS.getCode(), Authorization.LOGIN_SUCCESS.getDesc());
		} else {
			String allowErrorCount = CacheUtil.get(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT");
			logger.info("用户 [ {} ] 当前登录认证失败次数 [ {} ]", new Object[] { account, allowErrorCount });
			if (StringUtils.isBlank(allowErrorCount)) {
				CacheUtil.set(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT", "1",
						CacheSupport.SESSION_TIMEOUT, TimeUnit.SECONDS);
				response = build(false, Authorization.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
						Authorization.ACCOUNT_OR_PASSWORD_ERROR.getDesc());
			} else {
				// 登录尝试次数++
				int allowPasswordErrorCount = Integer.parseInt(allowErrorCount == null ? "0" : allowErrorCount) + 1;
				int maxPasswordErrorCount = Integer.parseInt(appConfig.getValue("authorize.password_max_error_count"));
				if (allowPasswordErrorCount > maxPasswordErrorCount) {
					// 记录系统限制日志
					doSaveOperateLog(account, Account.LOCK.getStatus());
					
					// 更新用户状态（锁定）
					setAccountStatus(user.getAccount(), Account.LOCK.getStatus());
					
					response = build(false, Authorization.PASSWORD_ERROR_REACHED_MAX_LIMIT.getCode(),
							Authorization.PASSWORD_ERROR_REACHED_MAX_LIMIT.getDesc());
				} else {
					CacheUtil.set(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT",
							String.valueOf(allowPasswordErrorCount), CacheSupport.SESSION_TIMEOUT, TimeUnit.SECONDS);
					response = build(false, Authorization.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
							Authorization.ACCOUNT_OR_PASSWORD_ERROR.getDesc());
				}
			}
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/** 向系统管理员发送提醒短信 */
	private void sendSmsToAdmin(String account) {
		User user = userService.queryByAccount("admin");
		
		String template = smsService.queryById(appConfig.getValue("sms.login_notice")).getMsgText();
		String message = String.format(template, account);
		
		// 短信提醒功能扩展（后续）
		List<User> adminList = new ArrayList<User>();
		adminList.add(user);
		for (User admin : adminList) {
			SmsUtil.send(admin.getMobile(), message);
		}
	}

	private void flushCache(HttpServletRequest request, User user) throws JsonProcessingException {
		final String account = user.getAccount();
		final String jSessionId = CacheUtil.get(USER_CACHE_PREFIX + account + ":JSESSIONID");
		CacheUtil.del(USER_CACHE_PREFIX + jSessionId);
		CacheUtil.set(USER_CACHE_PREFIX + account + ":JSESSIONID", request.getRequestedSessionId());
		CacheUtil.set(USER_CACHE_PREFIX + account, JacksonUtil.objectToJson(user));
		CacheUtil.set(USER_CACHE_PREFIX + account + ":PASSWORD:ALLOW_ERROR_COUNT", "0", CacheSupport.SESSION_TIMEOUT,
				TimeUnit.SECONDS);
		CacheUtil.set(USER_CACHE_PREFIX + account + ":CAPTCHA:ALLOW_ERROR_COUNT", "0", CacheSupport.SESSION_TIMEOUT,
				TimeUnit.SECONDS);
		CacheUtil.set(USER_CACHE_PREFIX + request.getRequestedSessionId(), account);
	}

	/** 设置用户状态 */
	private void setAccountStatus(String account, String status) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("account", account);
		args.put("status", status);
		userService.updateByAcct(args);
	}

	/** 校验登录账号和密码 */
	private boolean checked(User user, String account, String password) {
		return user.getAccount().equals(account) && user.getPassword().equals(password.toUpperCase());
	}

	/** 校验登录终端地址 */
	private Map<String, Object> validateTerminalAddress(HttpServletRequest request, User user) {
		Map<String, Object> validation = new HashMap<String, Object>();
		try {
			String ip = ServerUtil.getRemoteAddr(request);
			logger.debug("登录终端地址 [ {} ]", ip);
			if (!ip.equals(user.getIp())) {
				validation = build(false, Authorization.TERMINAL_ADDRESS_EXCEPTION.getCode(),
						Authorization.TERMINAL_ADDRESS_EXCEPTION.getDesc());
			} else {
				validation = build(true);
			}
		} catch (Exception e) {
			logger.error("获取终端信息异常, 异常信息 [ {} ]", e.getMessage(), e);
		}
		return validation;
	}

	/** 校验短信验证码 */
	private Map<String, Object> validateCaptcha(String account, String validCode) {
		Map<String, Object> validation = new HashMap<String, Object>();
		String captcha = CacheUtil.get(USER_CACHE_PREFIX + account + ":CAPTCHA");
		if (!validCode.equals(captcha)) {
			String allowErrorCount = CacheUtil.get(USER_CACHE_PREFIX + account + ":CAPTCHA:ALLOW_ERROR_COUNT");
			int currentErrorCount = Integer.parseInt(allowErrorCount == null ? "0" : allowErrorCount) + 1;
			int maxCaptchaErrorCount = Integer.parseInt(appConfig.getValue("authorize.captcha_max_error_count"));
			if (currentErrorCount > maxCaptchaErrorCount) {
				validation = build(false, Authorization.SMS_CAPTCHA_ERROR_REACHED_MAX_LIMIT.getCode(),
						Authorization.SMS_CAPTCHA_ERROR_REACHED_MAX_LIMIT.getDesc());
			} else {
				validation = build(false, Authorization.SMS_CAPTCHA_ERROR.getCode(),
						Authorization.SMS_CAPTCHA_ERROR.getDesc());
				validation.put("allow_error_count", currentErrorCount);
			}
		} else {
			validation = build(true);
		}
		return validation;
	}

	/** 校验请求参数 */
	private Map<String, Object> validateRequestParams(String account, String password, String validCode)
			throws IOException, JsonGenerationException, JsonMappingException {
		Map<String, Object> validation = new HashMap<String, Object>();
		if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(validCode)) {
			validation = build(false, Authorization.REQUEST_PARAMETER_LOST.getCode(),
					Authorization.REQUEST_PARAMETER_LOST.getDesc());
		} else {
			validation = build(true);
		}
		return validation;
	}

	private Map<String, Object> build(boolean result, String... error) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", result);
		if (error != null && error.length > 0) {
			if (StringUtils.isNotBlank(error[0])) {
				response.put("code", error[0]);
			}
			if (StringUtils.isNotBlank(error[1])) {
				response.put("message", error[1]);
			}
		}
		return response;
	}

	/** 登出系统 */
	@RequestMapping("logout")
	public String logout(String account) {
		CacheSupport.clean(account);
		return "redirect:login.jsp";
	}

	/** 首次登录强制修改初始密码 */
	@RequestMapping(value = "change", method = RequestMethod.GET)
	public String change(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("account", request.getParameter("account"));
		return "change";
	}

	/** 登录密码修改并刷新缓存 */
	@ResponseBody
	@RequestMapping("modify")
	public Object modify(HttpServletRequest request, String account, String password, String newPassword,
			String confirmPassword) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();

		// 必输项校验
		if (account == null || password == null || newPassword == null || confirmPassword == null) {
			response.put("result", false);
			response.put("code", "100");
			response.put("message", "账户或密码校验异常");
			return new ObjectMapper().writeValueAsString(response);
		}

		// 新旧密码相同校验
		if (password.equals(newPassword)) {
			response.put("result", false);
			response.put("code", "101");
			response.put("message", "新密码不能与旧密码设置相同");
			return new ObjectMapper().writeValueAsString(response);
		}

		// 新密码与确认密码不一致校验
		if (!newPassword.equals(confirmPassword)) {
			response.put("result", false);
			response.put("code", "102");
			response.put("message", "新密码与确认密码校验不一致");
			return new ObjectMapper().writeValueAsString(response);
		}

		if (doCheckExist(account, password)) {
			// 新密码是否之前设置过校验
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("account", account);
			args.put("password", newPassword.toUpperCase());
			int passwordDetailCount = userRoleService.queryCountByPwdDetail(args);
			if (passwordDetailCount > 0) {
				response.put("result", false);
				response.put("code", "103");
				response.put("message", "新密码已经设置过, 不能与过往密码相同!");
				return new ObjectMapper().writeValueAsString(response);
			}

			// 更新初始密码
			User user = new User();
			user.setAccount(account);
			user.setPassword(newPassword.toUpperCase());
			user.setOldPassword(newPassword.toUpperCase());
			user.setFirstLogin("0");
			user.addPasswordExpireTime(30);
			int result = userService.synchronization(user);
			if (result > 0) {
				flushCache(request, user);

				response.put("result", true);
				response.put("code", "000");
				response.put("message", "修改成功");
				return new ObjectMapper().writeValueAsString(response);
			}
		} else {
			response.put("result", false);
			response.put("code", "104");
			response.put("message", "旧密码错误");
			return new ObjectMapper().writeValueAsString(response);
		}

		response.put("result", false);
		response.put("code", "999");
		response.put("message", "未知情况");
		return new ObjectMapper().writeValueAsString(response);
	}

	// 旧密码正确性校验
	private boolean doCheckExist(String account, String password) {
		User user = userService.queryByAccount(account);
		if (user != null && user.getPassword().equals(password.toUpperCase())) {
			return true;
		}
		return false;
	}

	// 短信验证码
	private final String captcha() {
		StringBuilder builder = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < 6; i++) {
			builder.append(rnd.nextInt(10));
		}
		return builder.toString();
	}
	
	// 记录系统阻断日志
	private void doSaveOperateLog(String account, String status) {
		if (StringUtils.isBlank(account)) {
			return;
		}
		
		User user = userService.queryByAccount(account);
		if (user != null) {
			OperateLog log = new OperateLog();
			log.setUserAcct(account);
			log.setUserName(user.getStaffName());
			log.setOperateAcct("999999");
			log.setOperateBefore(user.getStatus());
			log.setOperateAfter(status);
			operateLogService.saveOperateLog(log);
		}
	}

}
