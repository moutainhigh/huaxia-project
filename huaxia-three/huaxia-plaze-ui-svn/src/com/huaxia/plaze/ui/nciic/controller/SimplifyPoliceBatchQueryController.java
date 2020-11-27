package com.huaxia.plaze.ui.nciic.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/*简项公安查询--批量实时查询*/
@Controller
@RequestMapping("nciic/police/batch/query")
public class SimplifyPoliceBatchQueryController {

	private final static Logger logger = LoggerFactory.getLogger(SimplifyPoliceBatchQueryController.class);

	// 交易处理业务层
	@Resource
	private SimplifyPoliceService simplifyPoliceService;

	/** 简项公安查询--批量实时查询页面 */
	@RequestMapping("index")
	public String index() {
		return "nciic/police_batch_index";
	}

	/* 批量实时查询提交 */
	@RequestMapping("submit")
	@ResponseBody
	public String batchSubmit(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		User loginUser = null;
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);

			int result = simplifyPoliceService.saveObject(batchNo, files, loginUser);
			if (result <= 0) {
				return "提交失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof ApplicationException) {
				return e.getMessage();
			}
			return "提交失败";
		}

		return "提交成功!批次号[" + batchNo + "]";
	}

}
