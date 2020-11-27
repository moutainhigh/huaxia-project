package com.huaxia.plaze.ui.pboc.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.huaxia.plaze.common.ExportSource;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;
import com.huaxia.plaze.ui.pboc.service.LogQueryService;
import com.huaxia.util.ExcelExportUtil;

/**
 * 人行查询日志监控
 * 
 * @author yanan.li
 * @author zhiguo.li 2019.01.09 增加"个人信用查询报告查询登记簿"下载功能
 *
 */
@Controller
@RequestMapping("pboc/log")
public class LogQueryController {

	private final static Logger logger = LoggerFactory.getLogger(LogQueryController.class);

	private PageProperty page;

	@Resource
	private LogQueryService sysLogQueryService;

	/** 人行查询日志监控分页列表 */
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
			logger.debug("[查询日志监控] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		page.clearDataMap();

		// 用户名
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}
		// 用户姓名
		String staffName = request.getParameter("staffName");
		if (StringUtils.isNotBlank(staffName)) {
			page.addParameter("staffName", staffName);
		}
		// 操作开始时间
		String startTime = request.getParameter("startTime");
		if (StringUtils.isNotBlank(startTime)) {
			page.addParameter("startTime", startTime);
		}
		// 操作结束时间
		String endTime = request.getParameter("endTime");
		if (StringUtils.isNotBlank(endTime)) {
			page.addParameter("endTime", endTime);
		}
		// 被查询人姓名
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}
		// 查询结果
		String queryResult = request.getParameter("queryResult");
		if (StringUtils.isNotBlank(queryResult)) {
			page.addParameter("queryResult", queryResult);
		}
		// 查询原因
		String queryReason = request.getParameter("queryReason");
		if (StringUtils.isNotBlank(queryReason)) {
			page.addParameter("queryReason", queryReason);
		}
		// 查询方式
		String queryType = request.getParameter("queryType");
		if (StringUtils.isNotBlank(queryType)) {
			page.addParameter("queryType", queryType);
		}
		// 查询例外
		String queryExclude = request.getParameter("queryExclude");
		if (StringUtils.isNotBlank(queryExclude)) {
			page.addParameter("queryExclude", queryExclude);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[查询日志监控] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		int totalCount = sysLogQueryService.queryListCountByPage(page);
		List<SysLogQuery> logList = sysLogQueryService.queryListByPage(page);
		StringBuilder builder = new StringBuilder();
		for (SysLogQuery log : logList) {
			// 证件号码第4-14位用*号代替
			builder.append(log.getStaffCertNo());
			builder.replace(3, 14, "***********");
			log.setStaffCertNo(builder.toString());
			builder.delete(0, builder.length());
			builder.append(log.getCertNo());
			if (StringUtils.isNotBlank(log.getCertNo()) && log.getCertNo().length() > 3) {
				builder.replace(3, 14, "***********");
			}
			log.setCertNo(builder.toString());
			builder.delete(0, builder.length());
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);

		return "pboc/pboc_log_list";
	}

	/** 人行异常查询—例外查询 */
	@RequestMapping("rhyccx")
	public String findLogListByPage_rh(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
			logger.debug("[查询日志管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}
		page.clearDataMap();

		// 用户名
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}
		// 用户姓名
		String staffName = request.getParameter("staffName");
		if (StringUtils.isNotBlank(staffName)) {
			page.addParameter("staffName", staffName);
		}
		// 操作开始时间
		String startTime = request.getParameter("startTime");
		if (StringUtils.isNotBlank(startTime)) {
			page.addParameter("startTime", startTime);
		}
		// 操作结束时间
		String endTime = request.getParameter("endTime");
		if (StringUtils.isNotBlank(endTime)) {
			page.addParameter("endTime", endTime);
		}
		// 被查询人姓名
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}
		// 查询结果
		String queryResult = request.getParameter("queryResult");
		if (StringUtils.isNotBlank(queryResult)) {
			page.addParameter("queryResult", queryResult);
		}
		// 查询方式
		String queryType = request.getParameter("queryType");
		if (StringUtils.isNotBlank(queryType)) {
			page.addParameter("queryType", queryType);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[用户管理] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		page.addParameter("queryExclude", "1");
		int totalCount = sysLogQueryService.queryListCountByPageEx(page);
		List<SysLogQuery> logList = sysLogQueryService.queryListByPageEx(page);
		StringBuilder builder = new StringBuilder();
		for (SysLogQuery log : logList) {
			// 证件号码第4-14位用*号代替
			builder.append(log.getStaffCertNo());
			builder.replace(3, 14, "***********");
			log.setStaffCertNo(builder.toString());
			builder.delete(0, builder.length());
			builder.append(log.getCertNo());
			if (log.getCertNo().length() > 4) {
				builder.replace(3, 14, "***********");
			}
			log.setCertNo(builder.toString());
			builder.delete(0, builder.length());
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);

		return "pboc/pboc_exclude_list";
	}

	/** 实时阻断查询情况 */
	@RequestMapping("zdcx")
	public String findLogListByPageZdcx(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
			logger.debug("[查询日志管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[用户管理] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		page.clearDataMap();

		page.addParameter("queryActionOne", "7");
		page.addParameter("queryActionTwo", "8");
		int totalCount = sysLogQueryService.queryListCountByPageEx(page);
		List<SysLogQuery> logList = sysLogQueryService.queryListByPageEx(page);
		StringBuilder builder = new StringBuilder();
		for (SysLogQuery log : logList) {
			// 证件号码第4-14位用*号代替
			builder.append(log.getStaffCertNo());
			builder.replace(3, 14, "***********");
			log.setStaffCertNo(builder.toString());
			builder.delete(0, builder.length());
			builder.append(log.getCertNo());
			if (log.getCertNo().length() > 4) {
				builder.replace(3, 13, "***********");
			}
			log.setCertNo(builder.toString());
			builder.delete(0, builder.length());
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);

		return "pboc/pboc_prevent_query_list";
	}

	/** 实时阻断用户情况 */
	@RequestMapping("zdyhcx")
	public String findLogListByPageZdyhcx(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 分页参数
		page = new PageProperty();
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		page.clearDataMap();
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
			logger.debug("[查询日志管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[用户管理] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		page.addParameter("queryAction", "7");
		int totalCount = sysLogQueryService.queryListCountByPageEx(page);
		List<SysLogQuery> logList = sysLogQueryService.queryListByPageEx(page);
		StringBuilder builder = new StringBuilder();
		for (SysLogQuery log : logList) {
			// 证件号码第4-14位用*号代替
			builder.append(log.getStaffCertNo());
			builder.replace(3, 13, "***********");
			log.setStaffCertNo(builder.toString());
			builder.delete(0, builder.length());
			builder.append(log.getCertNo());
			if (log.getCertNo().length() > 4) {
				builder.replace(3, 13, "***********");
			}
			log.setCertNo(builder.toString());
			builder.delete(0, builder.length());
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", logList);

		return "pboc/pboc_prevent_user_list";
	}

	/**
	 * 个人信用查询报告查询登记簿下载
	 * 
	 * @throws IOException
	 * @author zhiguo.li
	 */
	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response, String params) throws IOException {
		final Map<String, Object> args = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(params)) {
			String[] fields = URLDecoder.decode(params, "UTF-8").split("\\$");
			for (String param : fields) {
				String[] field = param.split("\\|");
				if (field.length > 1) {
					args.put(field[0], field[1]);
				}
			}
		}

		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode("个人信用查询报告查询登记簿.xls", "UTF-8"));
		response.addHeader("Accept-Ranges", "bytes");

		if (args.size() > 0) {
			List<Map<String, String>> reportHeader = new ArrayList<Map<String, String>>();
			String[] headerArray = new String[] { "NO:编号", "QUERY_DATE:查询日期", "STAFF_NAME:查询人姓名", "STAFF_CERTNO:查询人ID",
					"QUERY_NAME:被查询人姓名", "QUERY_CERTNO:被查询人ID", "QUERY_REASON:查询原因", "QUERY_TYPE:查询方式",
					"QUERY_RESULT:查询结果", "QUERY_ACTION:操作动作" };
			for (String header : headerArray) {
				String[] reportFieldArray = header.split(":");
				if (reportFieldArray.length > 1) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("CODE", reportFieldArray[0]);
					map.put("NAME", reportFieldArray[1]);
					reportHeader.add(map);
				}
			}
			try {
				ExcelExportUtil.download(request, response, "个人信用信息基础数据库个人信用查询报告查询登记簿", reportHeader,
						new ExportSource() {

							@Override
							public List<Map<String, Object>> getDataSource() {
								StringBuilder builder = new StringBuilder();
								List<Map<String, Object>> list = sysLogQueryService.queryListByArguments(args);
								for (Map<String, Object> object : list) {
									Iterator<String> it = object.keySet().iterator();
									while (it.hasNext()) {
										String nextString = it.next();
										if ("QUERY_CERTNO".equals(nextString)) {
											String queryCertNo = String.valueOf(object.get("QUERY_CERTNO"));
											if (queryCertNo.length() > 3) {
												builder.append(queryCertNo);
												builder.replace(3, 14, "***********");
												queryCertNo = builder.toString();
												builder.delete(0, builder.length());
											}
											object.put("QUERY_CERTNO", queryCertNo);
										}
										if ("QUERY_TYPE".equals(nextString)) {
											String queryType = String.valueOf(object.get("QUERY_TYPE"));
											if ("0".equals(queryType)) {
												queryType = "单条实时查询";
											} else if ("1".equals(queryType)) {
												queryType = "单条实时查找";
											}
											object.put("QUERY_TYPE", queryType);
										}
										if ("QUERY_RESULT".equals(nextString)) {
											String queryResult = String.valueOf(object.get("QUERY_RESULT"));
											if ("0".equals(queryResult)) {
												queryResult = "待复核";
											} else if ("1".equals(queryResult)) {
												queryResult = "复核通过";
											} else if ("2".equals(queryResult)) {
												queryResult = "正在查询";
											} else if ("3".equals(queryResult)) {
												queryResult = "查询成功";
											} else if ("4".equals(queryResult)) {
												queryResult = "查询失败";
											} else if ("5".equals(queryResult)) {
												queryResult = "已退回";
											}
											object.put("QUERY_RESULT", queryResult);
										}
										if ("QUERY_ACTION".equals(nextString)) {
											String queryAction = String.valueOf(object.get("QUERY_ACTION"));
											if ("1".equals(queryAction)) {
												queryAction = "提交复核";
											} else if ("2".equals(queryAction)) {
												queryAction = "复核通过";
											} else if ("3".equals(queryAction)) {
												queryAction = "复核退回";
											} else if ("4".equals(queryAction)) {
												queryAction = "复核通过";
											} else if ("5".equals(queryAction)) {
												queryAction = "批量退回";
											} else if ("6".equals(queryAction)) {
												queryAction = "单条查找";
											} else if ("7".equals(queryAction)) {
												queryAction = "阻断用户";
											} else if ("8".equals(queryAction)) {
												queryAction = "阻断查询";
											}
											object.put("QUERY_ACTION", queryAction);
										}
									}
								}
								return list;
							}

						});
			} catch (Exception e) {
				logger.error("个人信用信息基础数据库个人信用查询报告查询登记簿下载异常:{}", new Object[] { e.getMessage() }, e);
			}
		}
	}

}
