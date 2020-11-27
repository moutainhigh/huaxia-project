package com.huaxia.plaze.ui.bairong.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatch;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchDetail;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchFile;
import com.huaxia.plaze.ui.bairong.service.ApplyLoanStrBatchService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 百融多头借贷的批量数据入表的功能
 * 
 * @author liuwei
 * @date 2019.06.10 上午
 */
@Controller
@RequestMapping("bairong/als/batch")
public class ApplyLoanStrBatchController {

	private final static Logger logger = LoggerFactory.getLogger(ApplyLoanStrBatchController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private ApplyLoanStrBatchService applyLoanStrBatchService;

	/** 多头借贷查询--批量实时查询页面 */
	@RequestMapping("index")
	public String index() {
		return "bairong/als_batch_index";
	}

	/**
	 * 多头借贷批量文件提交
	 * 
	 * @param request
	 * @param files
	 *            批量数据文件
	 * @return
	 * @throws org.codehaus.jackson.map.JsonMappingException
	 * @throws IOException
	 * @throws org.codehaus.jackson.map.JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping("submit")
	@ResponseBody
	public String saveBatchUploadFile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files)
			throws JsonGenerationException, org.codehaus.jackson.map.JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", true);
		if(request.getAttribute("submitFlag")!=null&&request.getAttribute("submitFlag").equals("false")){
			long allowFileSize = (long) request.getAttribute("allowFileSize");
			long fileMaxLength = (long) request.getAttribute("fileMaxSize");
			response.put("success", false);
			response.put("message", "当前文件"+allowFileSize+"M,上传文件大小不能超过"+fileMaxLength+"M");
			return new ObjectMapper().writeValueAsString(response);
		}
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		if (files != null && files.length > 0) {
			List<String> successfulFileList = new ArrayList<String>();
			List<String> failedFileList = new ArrayList<String>();
			String batchNo = BatchNoGenerator.generateNextNumber();
			int total = 0;
			logger.info("开始百融多头借贷批量数据文件处理[ {} ]", new Object[] { batchNo });
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				logger.info("准备处理第[ {} ]个文件[ {} ]", new Object[] { (i + 1), file.getOriginalFilename() });
				int result = 0;
				try {
					result = applyLoanStrBatchService.saveUploadRecord(file, batchNo, account);
					successfulFileList.add(file.getOriginalFilename());
					logger.info("批次[ {} ]文件[ {} ]处理成功", new Object[] { batchNo, file.getOriginalFilename() });
				} catch (Exception e) {
					failedFileList.add(file.getOriginalFilename());
					logger.error("批次[ {} ]文件[ {} ]处理失败，异常信息[ {} ]。",
							new Object[] { batchNo, file.getOriginalFilename(), e.getMessage() });
				}
				total += result;
			}

			logger.info("百融多头借贷批量数据文件处理完成，处理[ {} ]个，成功[ {} ]个，失败[ {} ]个",
					new Object[] { files.length, successfulFileList.size(), failedFileList.size() });

			if (total > 0) {
				// 数据批次信息持久化
				ApplyLoanStrBatch applyLoanStrBatch = new ApplyLoanStrBatch();
				applyLoanStrBatch.setCrtUser(account);
				applyLoanStrBatch.setBatchId(batchNo);
				applyLoanStrBatch.setBatchRecordSize(total);
				applyLoanStrBatchService.saveTrnBatch(applyLoanStrBatch);
			}

			response.put("success", successfulFileList);
			response.put("failure", failedFileList);

		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/**
	 * 百融多头借贷查询--批次查询主面
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
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
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = applyLoanStrBatchService.queryBatchListCountByPage(page);
		List<ApplyLoanStrBatch> list = applyLoanStrBatchService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);

		return "bairong/als_batch_list";
	}

	/** 百融多头借贷--查询文件明细 */
	@RequestMapping(value = "pagelist/batchfile")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		List<ApplyLoanStrBatchFile> list = applyLoanStrBatchService.queryBatchFileListByPage(batchId);
		modelMap.put("records", list);
		return "bairong/als_batch_file_list";
	}

	/** 百融多头借贷--查询文件记录明细 */
	@RequestMapping(value = "pagelist/filedetail")
	public String getFileDtail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchFileId = request.getParameter("batchFileId");
		List<ApplyLoanStrBatchDetail> list = applyLoanStrBatchService.queryBatchDetailListByPage(batchFileId);
		modelMap.put("records", list);
		return "bairong/als_batch_detail_list";
	}

	/** 百融多头借贷--根据身份证号进行查询数据展示 */
	@RequestMapping(value = "view/detail")
	public String getRecordDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "bairong/als_batch_detail_show";
	}
}
