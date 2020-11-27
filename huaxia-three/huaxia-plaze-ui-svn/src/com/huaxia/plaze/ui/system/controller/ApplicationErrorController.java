package com.huaxia.plaze.ui.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationErrorController {
	
	@RequestMapping("upload-error")
	public String index(ModelMap model) {
		model.put("message", "文件上传超出限制");
		return "error";
	}

}
