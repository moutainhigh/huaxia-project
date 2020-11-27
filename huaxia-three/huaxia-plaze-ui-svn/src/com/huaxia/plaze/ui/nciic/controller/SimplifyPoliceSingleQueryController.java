package com.huaxia.plaze.ui.nciic.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.ExecutorSupport;
import com.huaxia.plaze.ui.nciic.caller.TaskWithResult;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceSingle;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 简项公安查询&单条实时查询
 */
@Controller
@RequestMapping("nciic/police/single")
public class SimplifyPoliceSingleQueryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SimplifyPoliceSingleQueryController.class);

	@Resource
	private SimplifyPoliceService simplifyPoliceService;

	/** 单条实时查询&首页 */
	@RequestMapping("index")
	public String index() {
		return "nciic/police_single_index";
	}

	/** 单条实时查询&提交  */
	@RequestMapping("submit")
	@ResponseBody
	public String submitCheck(HttpServletRequest request, SimplifyPoliceSingle single) throws JsonProcessingException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = null;
		try {
			loginUser = JacksonUtil.jsonToObject(body, User.class);
		} catch (Exception e) {
			logger.error("JSON转换对象异常:{}", new Object[] { e.getMessage() }, e);
		}
		single.setCrtUser(loginUser.getAccount());
		
		String trnId = GuidUtil.getGuid();
		single.setTrnId(trnId);

		// 设置查询状态为正在查询
		single.setQueryStatus("0");

		// 设置一些用户信息 后面要用
		single.setStaffCertNo(loginUser.getStaffId());
		single.setStaffName(loginUser.getStaffName());
		single.setIp(loginUser.getIp());
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		int result = 0;
		try {
			result = simplifyPoliceService.saveObject(single);
		} catch (Exception e) {
			// 保存单条查询记录，中出错，系统异常，查询失败
			logger.error("简项公安单条查询请求[{}]持久化异常:{}", new Object[] { trnId, e.getMessage() }, e);
			result = -1;
		}
		if (result <= 0) {
			response.put("result", "failure");
			return new ObjectMapper().writeValueAsString(response);
		}
		
		try {
			// 静态线程池，最大二百个线程
			FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new TaskWithResult(single));
			ExecutorSupport.EXECUTOR.submit(futureTask);
			if (futureTask.get()) {
				response.put("result", "success");
				response.put("trnId", trnId);
			} else {
				response.put("result", "failure");
			}
		} catch (Exception e) {
			logger.error("线程池执行异常:{}", new Object[] { e.getMessage() }, e);
		}
		return new ObjectMapper().writeValueAsString(response);
	}
}
