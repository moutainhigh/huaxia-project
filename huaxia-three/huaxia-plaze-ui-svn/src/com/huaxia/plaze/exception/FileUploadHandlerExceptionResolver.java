package com.huaxia.plaze.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class FileUploadHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {
		ApplicationException ex = null;
		if (exception instanceof ApplicationException) {
			ex = (ApplicationException) exception;
		}
		if (exception instanceof MaxUploadSizeExceededException) {
			// 允许上传文件大小
			MaxUploadSizeExceededException e= (MaxUploadSizeExceededException)exception;
			long allowFileSize = e.getMaxUploadSize() / 1024 / 1024;
			ex = new ApplicationException("上传文件不能大于" + allowFileSize + "M");
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", ex.getMessage());
			mav.setViewName("error");
			return mav;
		}else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", exception.getMessage());
			mav.setViewName("error");
			return mav;
		}		
	}

}
