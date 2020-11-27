package com.huaxia.plaze.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huaxia.plaze.common.Cache;
import com.huaxia.util.CacheUtil;

/**
 * 授权操作拦截器
 * 
 * @author zhiguo.li
 *
 */
public class AuthorizeOperateIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 检查应用缓存中是否存在指定账号信息
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getRequestedSessionId());
		if (!request.getSession().isNew() && StringUtils.isBlank(account)) {
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + account + ":JSESSIONID");
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + account + ":PASSWORD:ALLOW_ERROR_COUNT");
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + account + ":CAPTCHA:ALLOW_ERROR_COUNT");
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + account + ":CAPTCHA");
			CacheUtil.del(Cache.NAMESPACE + ":USER:" + account);
			goLoginIndex(request, response, "登录用户被强制下线!");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	private void goLoginIndex(HttpServletRequest request, HttpServletResponse response, String message) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			writer.write("<html>");
			writer.write("<script type=\"text/javascript\">");
			if (message != null) {
				writer.write("alert(\"" + message + "\");");
			}
			writer.write("window.top.location.href=\"" + request.getContextPath() + "/login.jsp\";");
			writer.write("</script>");
			writer.write("</html>");
			writer.flush();
		} catch (IOException e) {
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
