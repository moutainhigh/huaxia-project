package com.huaxia.plaze.ui.system.controller;

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

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.AuthorizeLog;
import com.huaxia.plaze.ui.system.domain.OperateLog;
import com.huaxia.plaze.ui.system.service.AuthorizeLogService;
import com.huaxia.plaze.ui.system.service.OperateLogService;

/**
 * 日志管理
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "system/log")
public class LogManageController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserManageController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;
	
	@Resource
	private AuthorizeLogService authorizeLogService;
	
	@Resource
	private OperateLogService operateLogService;
	
	/** 登录日志分页列表 */
	@RequestMapping(value = "login/pageList")
	public String findLogListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

		logger.debug("[登录日志] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });

		// 业务参数
		page.clearDataMap();
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}
		String staffName = request.getParameter("staffName");
		if (StringUtils.isNotBlank(staffName)) {
			page.addParameter("staffName", staffName);
		}
		String certNo = request.getParameter("certNo");
		if (StringUtils.isNotBlank(certNo)) {
			page.addParameter("certNo", certNo);
		}
		String action = request.getParameter("action");
		if (StringUtils.isNotBlank(action)) {
			page.addParameter("action", action);
		}
		String startTime = request.getParameter("startTime");
		if (StringUtils.isNotBlank(startTime)) {
			page.addParameter("startTime", startTime);
		}
		String endTime = request.getParameter("endTime");
		if (StringUtils.isNotBlank(endTime)) {
			page.addParameter("endTime", endTime);
		}

		logger.debug("[登录日志] 查询参数[{}]", new Object[] { page.getDataMap() });

		int totalCount = authorizeLogService.queryListCountByPage(page);
		List<AuthorizeLog> logList = authorizeLogService.queryListByPage(page);
		StringBuilder builder = new StringBuilder();
		for (AuthorizeLog log : logList) {
			builder.replace(0, builder.length(), "");
			if (log.getCertNo() == null || "".equals(log.getCertNo())) {
				continue;
			}
			
			// 证件号码第4-14位用*号代替
			builder.append(log.getCertNo());
			builder.replace(3, 14, "***********");
			log.setCertNo(builder.toString());
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);
		
		return "system/login_log_list_page";
	}
	
	/** 用户操作日志分页列表 */
	@RequestMapping(value = "operate/pagelist")
	public String findOperateLogListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

		// 业务参数
		page.clearDataMap();
		String account1 = request.getParameter("account1");
		if (StringUtils.isNotBlank(account1)) {
			page.addParameter("account1", account1);
		}
		String userName = request.getParameter("userName");
		if (StringUtils.isNotBlank(userName)) {
			page.addParameter("userName", userName);
		}
		String operateBefore = request.getParameter("before");
		if (StringUtils.isNotBlank(operateBefore)) {
			page.addParameter("operateBefore", operateBefore);
		}
		String operateAfter = request.getParameter("after");
		if (StringUtils.isNotBlank(operateAfter)) {
			page.addParameter("operateAfter", operateAfter);
		}
		String account2 = request.getParameter("account2");
		if (StringUtils.isNotBlank(account2)) {
			page.addParameter("account2", account2);
		}
		String operateTime = request.getParameter("operateTime");
		if (StringUtils.isNotBlank(operateTime)) {
			page.addParameter("operateTime", operateTime);
		}
		
		logger.debug("[操作日志] 查询参数[{}]", new Object[] { page.getDataMap() });

		int totalCount = operateLogService.queryOperateListCountByPage(page);
		List<OperateLog> logList = operateLogService.queryOperateListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);
		
		return "system/operate_log_pagelist";
	}

}
