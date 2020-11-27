package com.huaxia.plaze.ui.id5.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huaxia.plaze.ui.id5.domain.Education;
import com.huaxia.plaze.ui.id5.service.EducationService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "id5/edu/query")
public class EducationQueryController {
	
	private final static Logger logger = LoggerFactory.getLogger(EducationQueryController.class);
	
	@Resource
	private EducationService educationService;
	
	@RequestMapping("detail")
	public String getResult(String trnId, ModelMap modelMap) {
		Education education = educationService.queryResultByTrnId(trnId);
		if (education == null) {
			education = new Education();
		}
		logger.debug("[学历信息] 学历查询结果[ {} ]", education == null ? false : true); 
		modelMap.put("records", JSONObject.fromObject(education));
		return "id5/education_detail_show";
	}

}
