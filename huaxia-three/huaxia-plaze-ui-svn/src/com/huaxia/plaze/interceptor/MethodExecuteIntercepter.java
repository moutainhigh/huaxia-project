package com.huaxia.plaze.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MethodExecuteIntercepter extends HandlerInterceptorAdapter {
	
	private final static Logger logger = LoggerFactory.getLogger(MethodExecuteIntercepter.class);

	private long executeStartTime;
	
	private long executeEndTime;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		executeStartTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		executeEndTime = System.currentTimeMillis();
		String url = request.getRequestURI();
		int start = url.lastIndexOf("/") + 1;
		int end = url.lastIndexOf(".");
		String method = url.substring(start, end);
		if (logger.isDebugEnabled()) {
			logger.debug("[统计] 执行方法[{}]时长[{}]ms", new Object[] {method, executeEndTime - executeStartTime});
		}
		super.postHandle(request, response, handler, modelAndView);
	}

}
