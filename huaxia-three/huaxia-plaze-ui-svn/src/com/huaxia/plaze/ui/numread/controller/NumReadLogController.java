package com.huaxia.plaze.ui.numread.controller;

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
import com.huaxia.plaze.ui.numread.domain.NumReadLog;
import com.huaxia.plaze.ui.numread.service.LogService;
/**
 * 人行数字解读 日志
 * @author liyanjie
 *
 */
@Controller
@RequestMapping("numread/log")
public class NumReadLogController {
	private final static Logger logger = LoggerFactory.getLogger(NumReadLogController.class);

	private PageProperty page;
	@Resource
	private LogService logServiceImpl;
	/**
	 * 展示日志查询页面
	 * @return
	 */
	@RequestMapping("index")
	public String showLogIndex(){
		return "numread/numread_log_list";
	}
	@RequestMapping("page/list")
	public String showPageList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		// 分页参数
		page = new PageProperty();
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
				// 查询模式
				String queryModle = request.getParameter("queryModel");
				if (StringUtils.isNotBlank(queryModle)) {
					page.addParameter("queryModel", queryModle);
				}
				// 操作员工号
				String StaffCertName = request.getParameter("crtUser");
				if (StringUtils.isNotBlank(StaffCertName)) {
					page.addParameter("crtUser", StaffCertName);
				}

				int totalCount = logServiceImpl.queryLogListCountByPage(page);
				List<NumReadLog> logList = logServiceImpl.queryLogListByPage(page);
				StringBuilder builder = new StringBuilder();
				if (logList != null && logList.size() > 0) {
					for (NumReadLog pql : logList) {
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
				return "numread/numread_log_list";
	}
	
}
