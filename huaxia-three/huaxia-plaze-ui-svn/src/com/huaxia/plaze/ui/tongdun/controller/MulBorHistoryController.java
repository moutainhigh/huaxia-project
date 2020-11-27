package com.huaxia.plaze.ui.tongdun.controller;

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
import com.huaxia.plaze.ui.pboc.service.QueryMonitorService;
import com.huaxia.plaze.ui.system.controller.AuthorizationController;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBase;
import com.huaxia.plaze.ui.tongdun.service.MulBorHistoryService;

/** 多头借贷查询（历史）*/
@Controller
@RequestMapping(value = "tongdun/mulbor/history")
public class MulBorHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(MulBorHistoryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private QueryMonitorService pbocQueryExMonitorService;

	@Resource
	private AuthorizationController authorizationController;

	@Resource
	private MulBorHistoryService mulBorHistoryQueryService;

	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "tongdun/mulbor_history_list";
	}

	/**
	 * 历史数据查询分页列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping("pagelist")
	public String ListByPageQuery(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String certNo = request.getParameter("certNo");
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
			logger.debug("[历史数据查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 证件号
		page.clearDataMap();
		page.addParameter("certNo", certNo);
		if (logger.isDebugEnabled()) {
			logger.debug("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		List<MulBorBase> logList = mulBorHistoryQueryService.selectListByPage(page);
		// 查询姓名
		for (int i = 0; i < logList.size(); i++) {
			logList.get(i).setCrtUser(mulBorHistoryQueryService.queryName(logList.get(i).getTrnId()));
		}
		modelMap.put("page", page);
		modelMap.put("records", logList);
		modelMap.put("showPage", "true");
		return "tongdun/mulbor_history_list";
	}

}
