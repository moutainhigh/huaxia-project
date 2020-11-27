package com.huaxia.plaze.ui.id5.controller;

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
import com.huaxia.plaze.ui.id5.caller.EducationCallWorker;
import com.huaxia.plaze.ui.id5.domain.EducationSingle;
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.JacksonUtil;

@Controller
@RequestMapping("id5/edu/single")
public class EducationSingleController {

	private final static Logger logger = LoggerFactory.getLogger(EducationSingleController.class);

	@Resource
	private EducationService educationService;

	@RequestMapping("index")
	public String index() {
		return "id5/education_single_index";
	}

	/**
	 * 提交查询 //返回值 2:24小时数据有效性 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 * 
	 */
	@RequestMapping("submit")
	@ResponseBody
	public Object submit(HttpServletRequest request, EducationSingle educationSingle)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> resultData = new HashMap<String, Object>();
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		educationSingle.setCrtUser(loginUser.getAccount());

		String trnId = GuidUtil.getGuid();
		educationSingle.setTrnId(trnId);
		educationSingle.setStaffCertNo(loginUser.getStaffId());
		educationSingle.setStaffName(loginUser.getStaffName());
		educationSingle.setIp(loginUser.getIp());

		// 设置查询状态为正在查询
		educationSingle.setQueryStatus("0");
		int result = -1;
		try {
			result = educationService.save(educationSingle);
			if (result == 1) {
				// 调用联机交易发起实时查询
				Map<String, String> args = new HashMap<String, String>();
				args.put("TRN_ID", trnId);
				args.put("CERT_TYPE", null);
				args.put("CERT_NO", educationSingle.getCertNo());
				args.put("NAME", educationSingle.getName());
				args.put("MOBILE", null);
				FutureTask<Boolean> futureTask = new FutureTask<Boolean>(
						new EducationCallWorker(educationService, args));
				ExecutorSupport.EXECUTOR.submit(futureTask);
/*				ExecutorSupport.EXECUTOR.shutdown();
*/				if (futureTask.get()) {
					result = 1;
				} else {
					result = 0;
				}
			}else if(result == 2){
				Map<String, Object> argsCertNo = new HashMap<String, Object>();
				argsCertNo.put("certNo", educationSingle.getCertNo());
				//本来是显示24小时数据的，去掉，进行弹出框提示
//				trnId =educationService.query24HoursDataTrnIDByParams(argsCertNo);
			}
		} catch (Exception e) {
			logger.error("[学历信息] 单条实时查询异常[{}]", new Object[] { e.getMessage() }, e);
		}
		resultData.put("result", result);
		resultData.put("trnId", trnId);
		return new ObjectMapper().writeValueAsString(resultData);
	}

}
