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
import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;
import com.huaxia.plaze.ui.unicom.service.PhoneBatchService;

@Controller
@RequestMapping(value = "unicom/phone/log")
public class PhoneLogController {

	private final static Logger logger = LoggerFactory.getLogger(PhoneLogController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private PhoneBatchService phoneTrnService;

	/** 查询日志监控分页列表 */
	@RequestMapping("page/list")
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

		if (logger.isDebugEnabled()) {
			logger.debug("[手机实名认证查询日志监控] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		page.clearDataMap();

		// 被查询人姓名
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}
		// 被查询人证件号
		String crtNo = request.getParameter("crtNo");
		if (StringUtils.isNotBlank(crtNo)) {
			page.addParameter("crtNo", crtNo);
		}
		// 被查询人证件号
		String mobile = request.getParameter("mobile");
		if (StringUtils.isNotBlank(mobile)) {
			page.addParameter("mobile", mobile);
		}
		// 查询模式
		String queryModle = request.getParameter("queryType");
		if (StringUtils.isNotBlank(queryModle)) {
			page.addParameter("queryModle", queryModle);
		}
		// 操作员工号
		String StaffCertName = request.getParameter("StaffCertName");
		if (StringUtils.isNotBlank(StaffCertName)) {
			page.addParameter("StaffCertName", StaffCertName);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[手机实名认证查询日志监控] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		int totalCount = phoneTrnService.queryLogListCountByPage(page);
		List<PhoneQueryLog> logList = phoneTrnService.queryLogListByPage(page);
		StringBuilder builder = new StringBuilder();
		if (logList != null && logList.size() > 0) {
			for (PhoneQueryLog pql : logList) {
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

		return "unicom/log_list";
	}

}
