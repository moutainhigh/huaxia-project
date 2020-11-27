package com.huaxia.plaze.ui.nciic.controller;

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
import com.huaxia.plaze.ui.nciic.domain.NciicXpInfo;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 公安人像比对历史数据查询
 * 
 * @author liuwei
 *
 */
@Controller
@RequestMapping(value = "nciic/xp/history")
public class XpHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(XpHistoryController.class);
	
	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private SimplifyPoliceService simplifyPoliceService;

	/**
	 * 历史数据查询分页列表
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 * @throws Exception
	 */
	@RequestMapping("pagelist")
	public String hdListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		page = new PageProperty();

		String gmsfhm = request.getParameter("gmsfhm");
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
			logger.debug("[人像查询日志监控] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		page.clearDataMap();

		// 证件号
		page.clearDataMap();
		page.addParameter("gmsfhm", gmsfhm);
		if (logger.isDebugEnabled()) {
			logger.debug("[人像历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		int totalCount = simplifyPoliceService.queryXpHistoryListCountByPage(page);
		List<NciicXpInfo> hisList = simplifyPoliceService.queryXpHistoryListByPage(page, loginUser);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", hisList);
		return "nciic/xp_history_list";
	}

	/** 历史数据查询分页列表 */
	@RequestMapping("index")
	public String historyPage() {
		return "nciic/xp_history_index";
	}

}
