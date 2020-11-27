package com.huaxia.plaze.ui.unicom.controller;

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
import com.huaxia.plaze.ui.unicom.domain.PhoneHistory;
import com.huaxia.plaze.ui.unicom.service.PhoneBatchService;
import com.huaxia.plaze.ui.unicom.service.PhoneLogService;

/**
 * 历史数据查询
 * 
 * @author yanan.li
 *
 */
@Controller
@RequestMapping(value = "unicom/phone/history")
public class PhoneHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(PhoneHistoryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private PhoneBatchService phoneBatchService;

	@Resource
	private PhoneLogService phoneLogService;

	/** 历史数据查询 */
	@RequestMapping("index")
	public String index() {
		return "unicom/phone_history_list";
	}

	/** 历史查询列表 */
	@RequestMapping("pagelist")
	public String queryListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

		page.clearDataMap();

		// 证件号
		page.clearDataMap();
		page.addParameter("certNo", certNo);
		if (logger.isDebugEnabled()) {
			logger.debug("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		int totalCount = phoneBatchService.queryHistoryListCountByPage(certNo);
		List<PhoneHistory> historyList = phoneBatchService.HistoryListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", historyList);
		return "unicom/phone_history_list";
	}

}
