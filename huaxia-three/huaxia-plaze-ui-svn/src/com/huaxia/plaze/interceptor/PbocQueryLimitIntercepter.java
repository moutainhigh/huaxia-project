package com.huaxia.plaze.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huaxia.util.SpringContextUtil;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

public class PbocQueryLimitIntercepter extends HandlerInterceptorAdapter {

	private SingleQueryService singleQueryService = SpringContextUtil.getBean("singleQueryService");

	/**
	 * <dl>
	 * <dt>查询例外queryExclude取值情况：</dt>
	 * <dd>0-正常</dd>
	 * <dd>1-例外</dd>
	 * </dl>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String message = singleQueryService.checkAuth(loginUser.getUserId());
		if (message != null && !"例外".equals(message)) {
			response.setStatus(500);
			response.getWriter().print(message);
			return false;
		} else if ("例外".equals(message)) {
			request.setAttribute("queryExclude", "1");
			return true;
		}
		request.setAttribute("queryExclude", "0");
		return true;
	}

}
