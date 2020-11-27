package com.huaxia.opas.action.pad;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.huaxia.opas.service.credit.PatchboltService;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PadHttpServer
 */
public class PadSetStatusHttpServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(PadSetStatusHttpServer.class);
	ApplicationContext ac = new ClassPathXmlApplicationContext(
			new String[] { "config/naps-config.xml", "config/dubbo-reference.xml" });
	PatchboltService patchboltService = (PatchboltService) ac.getBean("patchboltService");

	public PadSetStatusHttpServer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info("pad进入servlet=========================");
		}
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = new PrintWriter(response.getOutputStream());
		String json = request.getParameter("json");// pad传的参数
		if (logger.isInfoEnabled()) {
			logger.info("pad传过来的参数json{}=====", json);
		}
		json = URLDecoder.decode(json, "utf-8");
		JSONObject params = JSONObject.fromObject(json);
		String autoId = params.getString("id");
		String completedFlag = params.getString("completedFlag");
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("completedFlag", completedFlag);
		paramMap.put("autoId", autoId);
		Map<String, String> map = new HashMap<>();
		String ysFlag = null;
		if (params.has("ysFlag")){
			ysFlag = params.getString("ysFlag");
		}
		if (logger.isInfoEnabled()) {
			logger.info("执行的map{}===", map);
		}
		try {
			
			if (null != ysFlag && !"".equals(ysFlag) && "1".equals(ysFlag)) {
				//此处判断是预审进来的
				if (null != autoId && !"".equals(autoId)) {
					logger.info("id:"+autoId+"进行预审操作");
					// 根据autoId去标准卡补件信息表里面去查是否存在这样的主键
					int count = patchboltService.queryYsCountById(autoId);
					if (count > 0) {
						patchboltService.updateYsByAutoId(paramMap);// 纯正标卡
					} else {
						//预审件无易达金卡操作
						logger.error("(预审中无ydj件)预审标准卡中找不到该返回件,返回id："+autoId);
					}
					map.put("code", "9000");
				} else {
					if (logger.isInfoEnabled()) {
						logger.info("预审pad传入的id为空");
					}
					map.put("code", "1000");
				}
			} else { // 此处判断是信审进来的，信审正常操作
				if (null != autoId && !"".equals(autoId)) {
					logger.info("id:"+autoId+"进行信审操作");
					// 根据autoId去标准卡补件信息表里面去查是否存在这样的主键
					int count = patchboltService.queryCountById(autoId);
					if (count > 0) {
						patchboltService.updateByAutoId(paramMap);// 纯正标卡
					} else {
						patchboltService.updateYdjByAutoId(paramMap);
						String appId = patchboltService.selectAppIdByAutoId(paramMap);
						//原有信审判断一下套卡id
						if(appId!=null&&!("").equals(appId)){
							if("1".equals(appId.substring(15,16))){
								paramMap.put("appId", appId.substring(0, 15) + "2");
							}else{
								paramMap.put("appId", appId.substring(0, 15) + "1");
							}
							logger.info("appId:"+appId+"有套卡，修改套卡补件状态");
							patchboltService.updateBzkByAppId(paramMap);
							map.put("code", "9000");
						}else{
							logger.info("id:"+autoId+"不存在信审系统中，请重新查询");
							map.put("code", "1000");
						}
						
					}
				} else {
					if (logger.isInfoEnabled()) {
						logger.info("信审pad传入的id为空");
					}
					map.put("code", "1000");
				}
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("进入servlet的了，出现异常===", e.getMessage());
			}
			map.put("code", "1000");
			throw e;
		} finally{
			String jsonMap = JSON.toJSONString(map);
			if (logger.isInfoEnabled()) {
				logger.info("返回给pad的map===={}", jsonMap);
			}
			out.print(jsonMap);
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info("pad进入post方法=====================================");
		}
		doGet(request, response);
	}

}
