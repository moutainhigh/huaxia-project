package com.huaxia.plaze.ui.unicom.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.ExecutorSupport;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.unicom.caller.PhoneCallWorker;
import com.huaxia.plaze.ui.unicom.domain.PhoneSingle;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;
import com.huaxia.plaze.ui.unicom.service.PhoneSingleService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONObject;

/**
 * 手机实名认证（单条）
 * 
 * @author zhiguo.li, 2019-08-15, 删除线程查询方式
 *
 */
@Controller
@RequestMapping("unicom/phone/single")
public class PhoneSingleController {

	private final static Logger logger = LoggerFactory.getLogger(PhoneSingleController.class);

	@Resource
	private PhoneSingleService phoneSingleService;

	/** 单条实时查询 */
	@RequestMapping("index")
	public String index() {
		return "unicom/phone_single_index";
	}

	/**
	 * 单条实时查询（提交查询）
	 * 
	 * 返回值1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 * 
	 */
	@RequestMapping("submit")
	@ResponseBody
	public Object submit(HttpServletRequest request, PhoneSingle phoneSingle)
			throws JsonParseException, JsonMappingException, IOException, ApplicationException {
		int result = 0;
		Map<String, Object> resultData = new HashMap<String, Object>();

		// 24小时数据有效性校验
		/*
		 * Map<String, Object> argsCertNo = new HashMap<String, Object>();
		 * argsCertNo.put("certNo", phoneSingle.getCertNo()); result =
		 * phoneSingleService.queryCountByParams(argsCertNo); if (result > 0) {
		 * return 2; }
		 */

		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		String trnId = GuidUtil.getGuid();
		phoneSingle.setTrnId(trnId);
		phoneSingle.setCrtUser(account);
		phoneSingle.setIp(loginUser.getIp());
		phoneSingle.setQueryType("1");
		phoneSingle.setQueryStatus("0");
		phoneSingle.setStaffId(loginUser.getStaffId());

		try {
			result = phoneSingleService.save(phoneSingle);
			if (result == 1) {
				// 调用联机交易发起实时查询
				Map<String, String> args = new HashMap<String, String>();
				args.put("TRN_ID", trnId);
				args.put("CERT_TYPE", phoneSingle.getCertType());
				args.put("CERT_NO", phoneSingle.getCertNo());
				args.put("NAME", phoneSingle.getName());
				args.put("MOBILE", phoneSingle.getMobile());
				FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new PhoneCallWorker(phoneSingleService, args));
				ExecutorSupport.EXECUTOR.submit(futureTask);
				if (futureTask.get()) {
					result = 1;
				} else {
					result = 0;
				}
				// ExecutorSupport.EXECUTOR.shutdown();
			}
		} catch (Exception e) {
			result = 0;
			logger.error("[手机实名认证] 单条实时查询异常[{}]", new Object[] { e.getMessage() }, e);
		}
		resultData.put("result", result);
		resultData.put("trnId", trnId);
		return new ObjectMapper().writeValueAsString(resultData);

	}

	@RequestMapping("view/detail")
	public String getResult(String trnId, ModelMap modelMap) {
		UnicomPhoneData info = phoneSingleService.querySingleResultByTrnid(trnId);
		modelMap.put("records", JSONObject.fromObject(info));
		return "unicom/phone_detail_show";
	}
}
