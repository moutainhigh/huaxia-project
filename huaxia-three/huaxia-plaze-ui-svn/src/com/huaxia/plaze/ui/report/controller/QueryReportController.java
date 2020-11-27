package com.huaxia.plaze.ui.report.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.report.service.PbocReportService;

/**
 * 查询报表统计
 * 
 * @author zhiguo.li
 * @since 2019-01-14
 * @version 1.0
 *
 */
@Controller
@RequestMapping("report")
public class QueryReportController {

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private PbocReportService pbocReportService;

	/** 人行查询数量统计报表 */
	@RequestMapping("pboc/query/pagelist")
	public String findReportListByPage(HttpServletRequest request, ModelMap modelMap) {
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

		int totalCount = pbocReportService.queryListCountReportByPage(page);
		List<Map<String, Object>> reportList = pbocReportService.queryListReportByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", reportList);
		return "report/report_pboc_querycount_list";
	}
}
