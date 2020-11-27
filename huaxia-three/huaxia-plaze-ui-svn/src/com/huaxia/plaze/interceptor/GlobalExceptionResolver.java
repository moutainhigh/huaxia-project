package com.huaxia.plaze.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.huaxia.plaze.exception.ApplicationException;

/**
 * 全局异常处理
 * 
 * @author zhiguo.li
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, Exception exception) {
		ModelAndView mav = doSpecialExceptionResolve(exception, httpServletRequest);
		if (mav == null) {
			String message = "系统异常, 请联系管理员!";
			if (exception instanceof ApplicationException) {
				message = exception.getMessage();
			}
			mav = buildErrorResult(message, "error", httpServletRequest);
		}
		return mav;
	}

	private ModelAndView buildErrorResult(String message, String url, HttpServletRequest request) {
		logger.warn("请求处理失败，请求地址[ {} ]，失败原因[ {} ]", new Object[] { request.getRequestURI(), message });
		if (isAjaxRequest(request)) {
			return buildJsonResult(message);
		} else {
			return buildPlainResult(message, url);
		}
	}

	private ModelAndView buildJsonResult(String message) {
		ModelAndView mav = new ModelAndView();
		FastJsonJsonView view = new FastJsonJsonView();
		view.setAttributesMap((JSONObject) JSON.toJSON("{'message':" + message + "}"));
		mav.setView(view);
		return mav;
	}

	private ModelAndView buildPlainResult(String message, String url) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("message", message);
		return new ModelAndView(url, model);
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

	private ModelAndView doSpecialExceptionResolve(Exception exception, HttpServletRequest request) {
		if (exception instanceof MaxUploadSizeExceededException) {
			return buildErrorResult("上传文件超出大小限制", "error", request);
		}
		return null;
	}

}
