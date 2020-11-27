package com.huaxia.opas.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.huateng.neofp.IcsException;
import com.huateng.neofp.channel.ChannelContext;
import com.huateng.neofp.channel.ChannelInterceptor;
import com.huateng.neofp.channel.http.support.JsonUtils;
import com.huateng.neofp.core.ContextEx;
import com.huateng.neofp.util.GUID;
import com.huaxia.opas.dao.common.OpasConstants;
import com.huaxia.opas.domain.log.TransLog;

/**
 * 响应拦截器
 * 
 * @author zhiguo.li
 *
 */
public class HttpLogInterceptor implements ChannelInterceptor, InitializingBean {

	protected static Logger logger = LoggerFactory.getLogger(HttpLogInterceptor.class);

	public void onRequest(ChannelContext channelContext, ContextEx contextEx) throws IcsException {
	}

	public void onResponse(ChannelContext channelContext, ContextEx contextEx, Throwable throwable) {
		// 交易启动时间
		long start = contextEx.getTimestamp();

		// 前台传递过来的请求ID
		String requestId = null;
		try {
			requestId = contextEx.getData(OpasConstants.TRANS_ID);
		} catch (Exception e1) {
		}
		if (requestId == null) {
			requestId = new GUID().toString();
		}
		contextEx.setRequestId(requestId);

		// 交易流水
		TransLog transLog = (TransLog) contextEx.getAttribute(OpasConstants.X_TRANSLOG_Y);

		// 输出返回报文
		Map dataMap = contextEx.getDataMap();

		Map requestPayloadMap = (Map) channelContext.getRequestPayload();

		String transCode = null;
		String responseCode = null;
		String resultFlag = null;
		if (requestPayloadMap != null) {
			transCode = (String) requestPayloadMap.get(OpasConstants.TRANS_CODE);
		}

		// 处理返回码
		if (dataMap.containsKey(OpasConstants.ERROR_CODE)) {
			responseCode = (String) dataMap.get(OpasConstants.ERROR_CODE);
			resultFlag = OpasConstants.RESULT_FLAG_EXCEPTION;
		} else {
			responseCode = OpasConstants.SUCCESS;
			resultFlag = OpasConstants.RESULT_FLAG_SUCCESS;
		}

		if (logger.isErrorEnabled()) {
			// 交易结束时间
			long end = System.currentTimeMillis();

			// 处理响应报文
			Object responseValue = channelContext.getResponsePalyload();
			String responseStr = null;
			if (responseValue instanceof Map) {
				responseStr = String.valueOf(responseValue);
			} else {
				try {
					responseStr = new String((byte[]) responseValue, OpasConstants.UTF_8);
				} catch (Exception e) {
					responseStr = new String((byte[]) responseValue);
				}
			}

			StringBuilder response = new StringBuilder(256);
			response.append("response data:").append(OpasConstants.STR_LEFT).append(contextEx.getRequestId())
					.append(OpasConstants.STR_RIGHT).append(OpasConstants.STR_LEFT).append(transCode)
					.append(OpasConstants.STR_RIGHT).append(OpasConstants.STR_LEFT).append(responseStr)
					.append(OpasConstants.STR_RIGHT);

			long cost = end - start;

			if (logger.isInfoEnabled()) {
				logger.info("[" + transCode + "][" + responseCode + "][" + cost + "ms].");
			}

			if (transLog != null) {
				// 完整响应报文

				if (logger.isDebugEnabled()) {
					logger.debug("HttpLogInterceptor调用MQ服务开始");
				}

				try {
					// 调用MQ存储日志信息
					String nodeId = null;
					Object request = channelContext.getRequest();

					if (request != null && request instanceof HttpServletRequest) {
						nodeId = StringUtils.substring(((HttpServletRequest) request).getContextPath(), 1);
					}

					if (StringUtils.isBlank(transLog.getInsideAppNo())) {
						Object insideAppNo = dataMap.get(OpasConstants.INSIDE_APP_NO);
						if (insideAppNo == null) {
							insideAppNo = dataMap.get(OpasConstants.PROCESS_INS_NO);
						}
						transLog.setInsideAppNo(String.valueOf(insideAppNo));
					}

					transLog.setRequestId(requestId); // 请求ID

					transLog.setNodeId(nodeId); // 节点编号
					transLog.setDuration(cost); // 交易耗时

					transLog.setResultFlag(resultFlag); // 交易结果
					transLog.setReturnCode(responseCode); // 交易返回码

					transLog.setRequestTimeLong(start); // 请求开始时间

					// 创建MQ消息对象：PS：dubbo服务不支持特别复杂的数据解耦股
					Map<String, Object> data = new HashMap<String, Object>();

					data.put(OpasConstants.TRANS_CODE, OpasConstants.TRANS_CODE_SAVE2DB);
					data.put(OpasConstants.TRANS_LOG,
							new String(JsonUtils.jsonFromObject(transLog, OpasConstants.UTF8), OpasConstants.UTF_8));

					if (logger.isInfoEnabled()) {
						logger.info(OpasConstants.DEFAULT_JMS_TRANSPORT + "-" + OpasConstants.LOCAL_LOG_REQ + " = " + data);
					}
				} catch (Exception e) {
					logger.error("HttpLogInterceptor invoke mq server error!", e);
				}
				
				if (logger.isDebugEnabled()) {
					logger.debug("HttpLogInterceptor调用MQ服务结束\n");
				}

				if (logger.isInfoEnabled()) {
					logger.info(responseStr);
				}
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
	}

}
