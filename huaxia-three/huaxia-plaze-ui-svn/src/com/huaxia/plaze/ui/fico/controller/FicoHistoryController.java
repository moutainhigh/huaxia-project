package com.huaxia.plaze.ui.fico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huaxia.plaze.ui.fico.domain.FicoRiskLevel;
import com.huaxia.plaze.ui.fico.service.FicoHistoryService;
import com.huaxia.util.SHAUtil;

/** FICO查询（历史）*/
@Controller
@RequestMapping(value = "fico/history")
public class FicoHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(FicoHistoryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;
	
	@Resource
	private FicoHistoryService ficoHistoryService;

	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "fico/fico_history_list";
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
		//若是certNo更改，则从第1页开始
				Map<String, Object> args = new HashMap<String, Object>();
				args.putAll(page.getDataMap());
				String idNumber = (String) args.get("certNo");
				if (StringUtils.isNotBlank(idNumber)&&StringUtils.isNotBlank(idNumber)) {
					if(idNumber.equals(certNo)){
					}else{
						page.setPageNo(1);
					}
				}
		if (logger.isDebugEnabled()) {
			logger.debug("[历史数据查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 证件号
		page.clearDataMap();
		page.addParameter("certNo", certNo);
		if (logger.isDebugEnabled()) {
			logger.debug("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		int totalCount = ficoHistoryService.queryListCountByPage(page);
		List<FicoRiskLevel> logList = ficoHistoryService.selectListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);
		modelMap.put("showPage", "true");
		return "fico/fico_history_list";
	}

}
