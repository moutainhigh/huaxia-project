package com.huaxia.plaze.cache;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.common.Cache;
import com.huaxia.util.CacheUtil;

/**
 * 缓存统一操作支持
 * 
 * @author zhiguo.li
 *
 */
public final class CacheSupport {

	/** 手机验证码失效时间（默认5分钟） */
	public static long CAPTCHA_TIMEOUT = 300;

	/** 系统会话失效时间（默认45分钟） */
	public static long SESSION_TIMEOUT = 2700;

	static {
		AppConfig appConfig = AppConfig.getInstance();

		String captchaTimeout = appConfig.getValue("authorize.captcha_timeout");
		if (StringUtils.isNotBlank(captchaTimeout)) {
			CacheSupport.CAPTCHA_TIMEOUT = Long.parseLong(captchaTimeout);
		}

		String sessionTimeout = appConfig.getValue("authorize.session_timeout");
		if (StringUtils.isNotBlank(sessionTimeout)) {
			CacheSupport.SESSION_TIMEOUT = Long.parseLong(sessionTimeout);
		}
	}

	public static boolean clean(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equalsIgnoreCase(cookie.getName())) {
					String jSessionId = cookie.getValue();
					String key = CacheUtil.get(Cache.NAMESPACE + ":USER:" + jSessionId);
					if (StringUtils.isNotBlank(key)) {
						remove(key);
						CacheUtil.del(Cache.NAMESPACE + ":USER:" + jSessionId);
					}
					break;
				}
			}
		}
		return true;
	}

	// public static boolean clean(String jSessionId) {
	// if (StringUtils.isBlank(jSessionId)) {
	// return false;
	// }
	//
	// String key = CacheUtil.get(Cache.NAMESPACE + ":USER:" + jSessionId);
	// if (StringUtils.isNotBlank(key)) {
	// remove(key);
	// CacheUtil.del(Cache.NAMESPACE + ":USER:" + jSessionId);
	// }
	//
	// return true;
	// }

	public static boolean clean(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		String jSessionId = CacheUtil.get(Cache.NAMESPACE + ":USER:" + key + ":JSESSIONID");
		remove(key);
		if (StringUtils.isNotBlank(jSessionId)) {
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + jSessionId);
		}
		return true;
	}

	/**
	 * 删除指定 {@code key} 所关联的全部缓存
	 * 
	 * @param key
	 */
	private static void remove(String key) {
		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key + ":JSESSIONID");
		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key + ":PASSWORD:ALLOW_ERROR_COUNT");
		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key + ":CAPTCHA:ALLOW_ERROR_COUNT");
		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key + ":CAPTCHA");
//		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key + ":LOGIN_COUNT");
		CacheUtil.del(Cache.NAMESPACE + ":USER:" + key);
	}

}
