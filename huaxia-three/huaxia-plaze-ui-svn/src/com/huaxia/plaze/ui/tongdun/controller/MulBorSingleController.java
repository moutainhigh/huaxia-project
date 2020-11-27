package com.huaxia.plaze.ui.tongdun.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.ExecutorSupport;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.tongdun.caller.MulBorCallWorker;
import com.huaxia.plaze.ui.tongdun.domain.MulBorSingle;
import com.huaxia.plaze.ui.tongdun.service.MulBorConfigurateService;
import com.huaxia.plaze.ui.tongdun.service.MulBorSingleService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.JacksonUtil;

/** 多头借贷查询（单条）*/
@Controller
@RequestMapping("tongdun/mulbor/single")
public class MulBorSingleController {

	private final static Logger logger = LoggerFactory.getLogger(MulBorSingleController.class);

	@Resource
	private MulBorSingleService mulBorSingleService;
	
	@Resource
	private MulBorConfigurateService mulBorConfigurateService;

	/** 多头借贷查询--单条实时查询页面 */
	@RequestMapping("index")
	public String index() {
		return "tongdun/mulbor_single_index";
	}

	/**
	 * 单条实时查询页面--进行单条信息的查询
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */

	@RequestMapping("submit")
	@ResponseBody
	public Object submit(HttpServletRequest request, MulBorSingle object)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> resultData = new HashMap<String, Object>();
		int result = 0;
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String trnId = GuidUtil.getGuid();
		// 查询接口设置,判断查询时间和数量是否符合约束条件,单条查询,result 1可以查询 -1 不在时间范围内 -2 数量达到上限
	    result = mulBorConfigurateService.isAllowSingleQuery("0");
		if (result == 1) {
			MulBorSingle single = new MulBorSingle();
			single.setCrtUser(loginUser.getAccount());
			single.setTrnId(trnId);
			single.setCertType("00");
			single.setCertNo(object.getCertNo());
			single.setName(object.getName());
			single.setMobile(object.getMobile());
			single.setQueryReason("单条查询");
			single.setQueryStatus("0");
			try {
				int resultSave = mulBorSingleService.save(single);
				if (1 == resultSave) {
					// 调用联机交易发起实时查询
					Map<String, String> args = new HashMap<String, String>();
					args.put("TRN_ID", single.getTrnId());
					args.put("CERT_TYPE", single.getCertType());
					args.put("CERT_NO", single.getCertNo());
					args.put("NAME", single.getName());
					args.put("MOBILE", single.getMobile());
					FutureTask<Boolean> futureTask = new FutureTask<Boolean>(
							new MulBorCallWorker(mulBorSingleService, args));
					ExecutorSupport.EXECUTOR.submit(futureTask);
					if (futureTask.get()) {
						result = 1;
					} else {
						result = 0;
					}
				}
			} catch (Exception e) {
				result = 0;
				logger.error("[多头借贷] 单条实时查询异常[{}]", new Object[] { e.getMessage() }, e);
			}
		} 
		resultData.put("result", result);
		resultData.put("trnId", trnId);
		return new ObjectMapper().writeValueAsString(resultData);
	}
}
