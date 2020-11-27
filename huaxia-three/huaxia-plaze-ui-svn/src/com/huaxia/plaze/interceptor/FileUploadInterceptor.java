package com.huaxia.plaze.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FileUploadInterceptor extends HandlerInterceptorAdapter {
	
	private long fileMaxSize;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request != null && ServletFileUpload.isMultipartContent(request)) {
			ServletRequestContext ctx = new ServletRequestContext(request);
			long uploadFileSize = ctx.getContentLength();
			if (uploadFileSize > fileMaxSize) {
				long allowFileSize = uploadFileSize / 1024 / 1024;
				long fileMaxLength = fileMaxSize / 1024 / 1024;
				HttpServletRequest req = (HttpServletRequest) request;
				String url = req.getRequestURL().toString();
				// 包含这些的请求将不通过过滤器
				if (url.contains("bairong/als/batch/submit.do") ) {
					request.setAttribute("submitFlag", "false");
					request.setAttribute("allowFileSize", allowFileSize);
					request.setAttribute("fileMaxSize", fileMaxLength);
					return true;
				}
				throw new MaxUploadSizeExceededException(fileMaxSize);
			}
		}
		return true;
	}

	public void setFileMaxSize(long fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

}
