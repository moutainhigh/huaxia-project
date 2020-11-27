package com.huaxia.opas.action.avaya;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.avaya.Avaya;
import com.huaxia.opas.domain.avaya.AvayaLog;
import com.huaxia.opas.service.avaya.AvayaAutoDialingService;
import com.huaxia.opas.service.avaya.AvayaLogService;
import com.huaxia.opas.util.ServletProxy;
import com.huaxia.opas.util.AvayaClient;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONObject;

public class AvayaServlet extends ServletProxy implements Action {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AvayaServlet.class);

	@Autowired
	private AvayaAutoDialingService avayaAutoDialingService;
	@Autowired
	private AvayaLogService avayaLogService;

	@Value("${avaya.host}")
	private String avayaHost;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");

		String phoneNum = request.getParameter("phone");
		try {
			String ip = getIp(request);
			logger.info("获取到的IP:{}", ip);
			// 新建avaya对象 并setIp值 用以查询
			Avaya avaya = new Avaya();
			avaya.setIp(ip);
			if (StringUtils.isNotBlank(ip)) {
				List<Avaya> list = new ArrayList<Avaya>();
				list = avayaAutoDialingService.queryAvayaAutoDialingLimit(avaya, 0, 20);
				if (list != null && list.size() >= 0) {
					Avaya av = list.get(0);

					Map<String, String> responseMap = new HashMap<String, String>();
					// 调用智能语音征信的http接口
					AvayaClient avayaClient = new AvayaClient();
					String requestJson = toAvayaJson(av.getAvayaLoginId(), av.getAvayaPassWd(), av.getAvayaStationId(),
							phoneNum);
					logger.info("[审批Avaya客户端]：调用Avaya服务:" + avayaHost + "开始拨打电话:" + requestJson);
					String responseStr = avayaClient.postJsonToAvaya(avayaHost, requestJson);
					// 插入日志
					addAvayaLog(ip, phoneNum, av.getAvayaStationId());
					logger.info("[审批Avaya客户端]:返回的Avaya服务端消息为：" + responseStr);
					if (responseStr != null && !StringUtils.isBlank(responseStr)) {
						// 解析收到返回数据
						responseMap = parseAvayaHttpResponse(responseStr);

						if (responseMap.get("code").equals("000")) {
							logger.info("[审批Avaya客户端]：拨打电话成功+{}" + phoneNum);
							response.setContentType("text/html;charset=utf-8");
							response.getWriter().write("true");

						} else {
							logger.info("[审批Avaya客户端] 服务端返回消息为:" + responseMap.get("message") + phoneNum);
							response.setContentType("text/html;charset=utf-8");
							response.getWriter().write("false");
						}
					} else {
						logger.warn("[审批Avaya客户端] 服务端返回消息为空");
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().write("false");
					}

				} else {
					logger.warn("*** 自动拨号：数据库未正确配置avaya账号和IP对应关系+{}" + ip);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write("false");
				}
			} else {
				logger.warn("*** 自动拨号：无法获取正确IP地址 ***");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("false");

			}
		} catch (Exception e) {
			logger.error("*** 自动拨号：拨打电话:" + phoneNum + "异常", e);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("false");
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!check(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}

		if (!check(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}

		if (check(ip) && ip.indexOf(",") != -1) {
			ip = ip.split(",")[0]; // 经过代理会有多个IP，第1个是真实IP
		}
		if (!check(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!check(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!check(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (!check(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (!check(ip)) {
			ip = request.getRemoteAddr();
		}

		logger.info("x-forwarded-for===" + request.getHeader("x-forwarded-for"));
		logger.info("X-Forwarded-For===" + request.getHeader("X-Forwarded-For"));
		logger.info("Proxy-Client-IP===" + request.getHeader("Proxy-Client-IP"));
		logger.info("WL-Proxy-Client-IP===" + request.getHeader("WL-Proxy-Client-IP"));
		logger.info("HTTP_CLIENT_IP===" + request.getHeader("HTTP_CLIENT_IP"));
		logger.info("HTTP_X_FORWARDED_FOR=" + request.getHeader("HTTP_X_FORWARDED_FOR"));
		logger.info("X-Real-IP=" + request.getHeader("X-Real-IP"));
		logger.info("request.getRemoteAddr=" + request.getRemoteAddr());
		logger.info("X-Forwarded-For=" + request.getHeader("X-Forwarded-For"));
		return ip;
	}

	private static boolean check(String ip) {
		if (ip != null && ip.length() != 0 && !ip.equalsIgnoreCase("unknown")) {
			return true;
		}
		return false;
	}

	/**
	 * 组装avaya接口报文
	 * 
	 * @param avayaId:用户名
	 *            avayaPwd:密码 stationId:分机号 phoneNum:手机号
	 * @return jsonStr
	 */
	private static String toAvayaJson(String avayaId, String avayaPwd, String stationId, String phoneNum) {

		try {
			Map<String, Object> param = new HashMap<String, Object>();

			param.put("userName", avayaId);
			param.put("passWord", avayaPwd);
			param.put("stationId", stationId);
			param.put("phoneNum", phoneNum);
			return JSON.toJSONString(param);
		} catch (Exception e) {
			logger.error("[审批Avaya客户端]：申请件报文组装异常", e);
			return null;
		}

	}

	/**
	 * 
	 * 解析Avaya服务端的http接口：返回信息 000:拨打电话成功 991:拨打电话失败 997:服务端接收客户端消息为空
	 * 999:拨打电话报文接收异常
	 */
	public static Map<String, String> parseAvayaHttpResponse(String jsonStr) {

		Map<String, String> params = new HashMap<>();
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(jsonStr);
			if (jsonObject != null) {
				if (jsonObject.containsKey("code") && jsonObject.getString("code") != null
						&& !"".equals(jsonObject.getString("code"))) {
					params.put("code", jsonObject.getString("code"));
				}
			}
			if (params.get("code").equals("991")) {
				params.put("message", "拨打电话失败");
			} else if (params.get("code").equals("997")) {
				params.put("message", "服务端接收客户端消息为空");
			} else if (params.get("code").equals("999")) {
				params.put("message", "拨打电话异常");
			}

		} catch (Exception e) {
			logger.error("[审批Avaya客户端]：接收的服务端返回数据为：" + jsonStr + "返回参数解析异常 ", e);
		}
		return params;
	}

	public void addAvayaLog(String ip, String phoneNum, String StationId) {
		AvayaLog avayaLog = new AvayaLog();
		avayaLog.setId(SequenceUtil.getUUID());
		avayaLog.setAvayaIp(ip);
		avayaLog.setAvayaStationId(StationId);
		avayaLog.setPhoneNum(phoneNum);

		try {
			avayaLogService.addAvayaLog(avayaLog);
			logger.info("[审批Avaya客户端]：插入日志到数据库成功！");
		} catch (CoreException e) {
			logger.error("[审批Avaya客户端]：插入日志到数据库失败！" , e);
		}
	}
}
