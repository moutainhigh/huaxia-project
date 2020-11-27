package com.huaxia.plaze.ui.id5.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.id5.domain.EducationBatch;
import com.huaxia.plaze.ui.id5.domain.EducationBatchDetail;
import com.huaxia.plaze.ui.id5.domain.EducationBatchFile;
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

@Controller
@RequestMapping("id5/edu/batch")
public class EducationBatchController {

	private final static Logger logger = LoggerFactory.getLogger(EducationBatchController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private EducationService educationService;

	@RequestMapping("index")
	public String index() {
		return "id5/education_batch_index";
	}

	/* 批量实时查询提交 */
	@RequestMapping("submit")
	@ResponseBody
	public String submit(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		User loginUser = null;
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);
			// 返回值１：可以查询 返回值- 1：不在时间段内 返回值-2：超出数量限制 返回值
			// 0:失败
			int result = educationService.saveBatch(batchNo, files, loginUser);
			if (result == -1) {
				return "不在可查询时间段内！";
			} else if (result == -2) {
				return "该时间段内查询数量已达上限(包括现在所导入文件数量)";
			} else if (result == 0) {
				return "提交失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				return e.getMessage();
			}
			return "提交失败";
		}
		return "提交成功    生成批次号为" + batchNo;
	}

	/** 批次查询列表 */
	@RequestMapping("pagelist/batch")
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
			logger.debug("[批次查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = educationService.queryBatchListCountByPage(page);
		List<EducationBatch> list = educationService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "id5/education_batch_list";
	}

	/** 批次文件查询列表 */
	@RequestMapping(value = "pagelist/batchfile")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		List<EducationBatchFile> list = educationService.queryFileDetail(batchId);
		modelMap.put("records", list);
		return "id5/education_batchfile_list";
	}

	/** 批次文件明细查询记录 */
	@RequestMapping(value = "pagelist/filedetail")
	public String getDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchFileId = request.getParameter("batchFileId");
		List<EducationBatchDetail> list = educationService.queryDetail(batchFileId);
		modelMap.put("records", list);
		return "id5/education_batchdetail_list";
	}

}
