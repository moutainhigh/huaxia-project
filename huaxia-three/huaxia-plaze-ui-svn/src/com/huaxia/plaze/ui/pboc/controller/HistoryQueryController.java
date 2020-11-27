package com.huaxia.plaze.ui.pboc.controller;

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

import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.HistoryQuery;
import com.huaxia.plaze.ui.pboc.service.HistoryQueryService;
import com.huaxia.plaze.ui.pboc.service.QueryMonitorService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.pboc.service.CreditReportService;
import com.huaxia.plaze.ui.pboc.support.QueryLogSupport;
import com.huaxia.plaze.ui.system.controller.AuthorizationController;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 人行征信查询（历史）
 * 
 * @author yanan.li
 * 
 */
@Controller
@RequestMapping(value = "pboc/history")
public class HistoryQueryController {

	private final static Logger logger = LoggerFactory.getLogger(HistoryQueryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private HistoryQueryService historyQueryService;

	@Resource
	private CreditReportService pbocQueryResultService;

	@Resource
	private QueryMonitorService pbocQueryExMonitorService;

	@Resource
	private AuthorizationController authorizationController;

	@Resource
	private QueryLogSupport queryLogSupport;
	
	@Resource
	private SingleQueryService singleQueryService;
	
	/** 历史数据查询主页面 */
	@RequestMapping("index")
	public String index() {
		return "pboc/pboc_history_query";
	}

	/** 历史数据查询分页列表 */
	@RequestMapping("pagelist")
	public String queryListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String queryExclude = (String) request.getAttribute("queryExclude");
		String certNo = request.getParameter("certNo");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String message = pbocQueryExMonitorService.checkLimitSearch(certNo, loginUser, queryExclude);
		if (message != null) {
			if (message.indexOf("下线") != -1) {
				authorizationController.logout(account);
			}
			modelMap.put("info", message);
			return "pboc/pboc_history_query";
		}
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			modelMap.put("info", mes);
			return "pboc/pboc_history_query";
		}
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		int iPageNo = page.getPageNo();
		
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		String pageSize = request.getParameter("pageSize");
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page =new PageProperty();
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		// 证件号
		page.clearDataMap();
		page.addParameter("certNo", certNo);
		
		logger.info("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		
		int totalCount = historyQueryService.queryListCountByPage(page);
		List<HistoryQuery> historyList = historyQueryService.queryListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", historyList);
		modelMap.put("showPage", "true");

		try {
			if ("例外".equals(mes)) {
				queryLogSupport.queryAfterSearch(certNo, "1", "6", "1");
			} else {
				queryLogSupport.queryAfterSearch(certNo, "1", "6", "0");
			}
//			queryLogSupport.queryAfterSearch(certNo, "1", "6", queryExclude);
		} catch (Exception e) {
			logger.error("[历史数据查询] 记录查询日志错误[{}]", new Object[] { e.getMessage() }, e);
		}
		return "pboc/pboc_history_query";
	}

}
