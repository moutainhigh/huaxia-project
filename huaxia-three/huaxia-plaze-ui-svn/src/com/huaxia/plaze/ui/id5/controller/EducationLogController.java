package com.huaxia.plaze.ui.id5.controller;

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
import com.huaxia.plaze.ui.id5.domain.EducationQueryLog;
import com.huaxia.plaze.ui.id5.service.EducationService;

@Controller
@RequestMapping(value = "id5/edu/log")
public class EducationLogController {

	private final static Logger logger = LoggerFactory.getLogger(EducationLogController.class);

	private PageProperty page;

	@Resource
	private EducationService educationService;

	/** 查询日志监控分页列表 */
	@RequestMapping("pagelist")
	public String findLogListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		page = new PageProperty();

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
			logger.debug("[学历日志查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		page.clearDataMap();

		// 用户账号
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}
		// 用户姓名
		String staffName = request.getParameter("staffName");
		if (StringUtils.isNotBlank(staffName)) {
			page.addParameter("staffName", staffName);
		}
		// 查询模式
		String queryMode = request.getParameter("queryMode");
		if (StringUtils.isNotBlank(queryMode)) {
			page.addParameter("queryMode", queryMode);
		}

		// 被查询人姓名
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}
		String certNo = request.getParameter("certNo");
		if (StringUtils.isNotBlank(certNo)) {
			page.addParameter("certNo", certNo);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[学历日志查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		int totalCount = educationService.queryLogListCountByPage(page);
		List<EducationQueryLog> logList = educationService.queryLogListByPage(page);
		StringBuilder builder = new StringBuilder();
		if (logList != null && logList.size() > 0) {
			for (EducationQueryLog pql : logList) {
				// 证件号码第4-14位用*号代替
				builder.append(pql.getCertNo());
				if (pql.getCertNo() != null) {
					if (pql.getCertNo().length() > 3) {
						builder.replace(3, 14, "***********");
					}
				}
				pql.setCertNo(builder.toString());
				builder.delete(0, builder.length());
			}
		}

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);

		return "id5/education_log_list";
	}

}
