package com.huaxia.plaze.ui.id5.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.id5.domain.Education;
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 学历历史数据查询
 * 
 * @author yanan.li
 *
 */
@Controller
@RequestMapping(value = "id5/edu/history")
public class EducationHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(EducationHistoryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private EducationService educationService;
	
	@RequestMapping("index")
	public String index() {
		return "id5/education_history_list";
	}

	@RequestMapping("pagelist")
	public String queryListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		page = new PageProperty();

		String identityCard = request.getParameter("identityCard");
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[手机实名认证查询日志监控] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		page.clearDataMap();

		// 证件号码
		page.clearDataMap();
		page.addParameter("identityCard", identityCard);
		if (logger.isDebugEnabled()) {
			logger.debug("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		int totalCount = educationService.queryHistoryListCountByPage(page);
		List<Education> hisList = educationService.queryHistoryListByPage(page, loginUser);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", hisList);
		return "id5/education_history_list";
	}

}
