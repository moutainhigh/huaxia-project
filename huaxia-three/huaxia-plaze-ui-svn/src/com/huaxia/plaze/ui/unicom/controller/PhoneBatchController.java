package com.huaxia.plaze.ui.unicom.controller;

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
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchDetail;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchFile;
import com.huaxia.plaze.ui.unicom.domain.UnicomBatchInfo;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;
import com.huaxia.plaze.ui.unicom.service.PhoneBatchService;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONObject;

/**
 * 手机实名认证（批量）
 * 
 * @author zhiguo.li, 2019-08-15, 重构优化
 *
 */
@Controller
@RequestMapping("unicom/phone/batch")
public class PhoneBatchController {

	private final static Logger logger = LoggerFactory.getLogger(PhoneBatchController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private PhoneBatchService phoneBatchService;

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

		int totalCount = phoneBatchService.queryBatchListCountByPage(page);
		List<UnicomBatchInfo> list = phoneBatchService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);

		return "unicom/phone_batch_list";
	}

	/** 批次文件查询列表 */
	@RequestMapping("pagelist/batchfile")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		List<PhoneBatchFile> list = phoneBatchService.queryFileDetail(batchId);
		modelMap.put("records", list);
		return "unicom/phone_batch_file_list";
	}

	/** 批次文件明细查询记录 */
	@RequestMapping("pagelist/filedetail")
	public String getDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchFileId = request.getParameter("batchFileId");
		List<PhoneBatchDetail> list = phoneBatchService.selectDetail(batchFileId);
		modelMap.put("records", list);
		return "unicom/phone_batch_detail_list";
	}

	/** 批次记录查询结果 */
	@RequestMapping("view/detail")
	public String getDetailResult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String trnId = request.getParameter("trnId");
		// List<UnicomPhoneData> list = phoneBatchService.getResult(trnId);
		UnicomPhoneData info = phoneBatchService.getResult(trnId);
		modelMap.put("records", JSONObject.fromObject(info));
		return "unicom/phone_detail_show";
	}

	/** 批量实时查询 */
	@RequestMapping("index")
	public String index() {
		return "unicom/phone_batch_index";
	}

	@RequestMapping("submit")
	@ResponseBody
	public String submit(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		User loginUser = null;
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);

			return phoneBatchService.saveBatch(batchNo, files, loginUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				return e.getMessage();
			}
			return "提交失败";
		}
		// return "提交成功 生成批次号为" + batchNo;
	}

}
