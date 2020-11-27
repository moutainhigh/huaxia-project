package com.huaxia.plaze.ui.nciic.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.nciic.domain.NciicInfo;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchDetail;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchFile;
import com.huaxia.plaze.ui.nciic.domain.NciicBatchReview;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/*简项公安查询--批次查询*/
@Controller
@RequestMapping("nciic/police/batch")
public class SimplifyPoliceBatchController {

	private final static Logger logger = LoggerFactory.getLogger(SimplifyPoliceBatchController.class);
	// 交易复核信息 处理业务层
	@Resource
	private SimplifyPoliceService simplifyPoliceService;

	@Resource(name = "pageProperty")
	private PageProperty page;

	/** 批次列表 */
	@RequestMapping("pagelist")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
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
		page.addParameter("team", loginUser.getTeam());

		if (logger.isDebugEnabled()) {
			logger.debug("[简项公安批次查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = simplifyPoliceService.queryBatchListCountByPage(page);
		List<NciicBatchReview> list = simplifyPoliceService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "nciic/police_batch_list";
	}

	/** 批次文件列表 */
	@RequestMapping(value = "batchfile/list")
	public String getBatchList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		List<SimplifyPoliceBatchFile> list = simplifyPoliceService.queryFileDetail(batchId);
		modelMap.put("records", list);
		return "nciic/police_batchfile_list";
	}

	/** 批次文件记录列表 */
	@RequestMapping(value = "batchfile/detail")
	public String getFileList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchFileId = request.getParameter("batchFileId");
		List<SimplifyPoliceBatchDetail> list = simplifyPoliceService.queryDetail(batchFileId);
		modelMap.put("records", list);
		return "nciic/police_batchfile_detail_list";
	}

	/** 批次文件记录明细 */
	@RequestMapping(value = "get/filedetail")
	public String getFileDetail(HttpServletRequest request, ModelMap modelMap) {
		String trnId = request.getParameter("trnId");
		NciicInfo info = simplifyPoliceService.queryResult(trnId);
		modelMap.put("info", info);
		return "nciic/police_detail_show";
	}

}
