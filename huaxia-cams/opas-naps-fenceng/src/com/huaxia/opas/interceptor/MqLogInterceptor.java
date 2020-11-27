package com.huaxia.opas.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.huateng.neofp.IcsException;
import com.huateng.neofp.channel.ChannelContext;
import com.huateng.neofp.channel.ChannelInterceptor;
import com.huateng.neofp.channel.http.support.JsonUtils;
import com.huateng.neofp.core.ContextEx;
import com.huaxia.opas.dao.common.OpasConstants;
import com.huaxia.opas.domain.log.TransLog;

/**
 * MQ响应报文拦截器
 * @author uatym990190
 */
@SuppressWarnings({ "rawtypes" })
public class MqLogInterceptor implements ChannelInterceptor, InitializingBean {

	protected static Logger logger = LoggerFactory.getLogger(MqLogInterceptor.class);
	
	public void onRequest(ChannelContext channelContext, ContextEx contextEx)
	throws IcsException {
	}

	public void onResponse(ChannelContext channelContext, ContextEx contextEx, Throwable throwable) {
		// 交易启动时间
		long start = contextEx.getTimestamp();
		
		// 交易流水
		TransLog transLog = (TransLog)contextEx.getAttribute(OpasConstants.X_TRANSLOG_MQ_Y);
		
//		// 清理掉上下午中的数据
//		contextEx.setAttribute(OpasConstants.X_TRANSLOG_MQ_Y, OpasConstants.EMPTY);
		
		if(
			transLog==null 
			|| OpasConstants.TRANS_CODE_SAVE2DB.equals(transLog.getTransCode())
		){
			// 忽略部分交易，该交易不忽略的话，会造成死循环
			return;
		}
		
		//输出返回报文
		Map dataMap = contextEx.getDataMap();
		
//		if (logger.isDebugEnabled()) {
//			Iterator iter = dataMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry entry = (Map.Entry)iter.next();
//				logger.debug(entry.getKey() + "=" + entry.getValue());
//			}
//		}

		//从requestMap中取出TRAN_ID
		//从dataMap中判断并取出errorCode，记录到monitor中
		//Map requestMap = (Map)contextEx.getAttributes().get("PARAMETERS");
		Map requestPayloadMap = (Map)channelContext.getRequestPayload();
		
		// 处理响应码
		String transCode = null;
		String responseCode = null;
		String resultFlag = null;
		if (requestPayloadMap != null && requestPayloadMap.containsKey(OpasConstants.TRANS_CODE)) {
			transCode = (String)requestPayloadMap.get(OpasConstants.TRANS_CODE);
		}
		if (dataMap.containsKey(OpasConstants.ERROR_CODE)) {
			responseCode = (String)dataMap.get(OpasConstants.ERROR_CODE);
			resultFlag = OpasConstants.RESULT_FLAG_EXCEPTION;
		} else {
			responseCode = OpasConstants.SUCCESS;
			resultFlag = OpasConstants.RESULT_FLAG_SUCCESS;
		}
		
		if (logger.isErrorEnabled()) {
			long end = System.currentTimeMillis();
			
			// 处理响应报文
//			Object responseValue = channelContext.getResponsePalyload();
//			String responseStr = null;
//			if(responseValue instanceof Map){
//				responseStr = String.valueOf(responseValue);
//			}else{
//				try {
//					responseStr = new String((byte [])responseValue, OpasConstants.UTF_8);
//				} catch (Exception e) {
//					responseStr = new String((byte [])responseValue);
//				}
//			}
			
//			StringBuilder response = new StringBuilder(256);
//			response.append("response data:")
//				.append(OpasConstants.STR_LEFT).append(contextEx.getRequestId()).append(OpasConstants.STR_RIGHT)
//				.append(OpasConstants.STR_LEFT).append(transCode).append(OpasConstants.STR_RIGHT)
//				.append(OpasConstants.STR_LEFT).append(responseStr).append(OpasConstants.STR_RIGHT)
//			;
			
			long cost = end-start;
			
			if (logger.isInfoEnabled()) {
				logger.info("["+transCode+"]["+responseCode+"]["+cost+"ms].");
			}
			
			if (transLog!=null) {
				// 完整响应报文
				if (logger.isDebugEnabled()) {
					logger.debug("MqLogInterceptor调用MQ服务开始");
				}
				
				try {
					// 获取队列编号
					String nodeId = (String) dataMap.get(OpasConstants.NODE_NO);
					if (nodeId==null) {
						// 如果 节点编号不存在，则取MQ队列编号
						Object request = channelContext.getRequest();
						if (request != null && request instanceof Message) {
							try {
								nodeId = StringUtils.substringAfter(((Message)request).getJMSDestination().toString(), "//");
							} catch (JMSException e) {
								// 异常忽略
							}
						}
					}
					
					// 内审编号
					String insideAppNo = (String) dataMap.get(OpasConstants.PROCESS_INS_NO);
					if (insideAppNo==null) {
						insideAppNo = (String) dataMap.get(OpasConstants.INSIDE_APP_NO);
					}
					
					//调用MQ存储日志信息
					Map<String,Object> data = new HashMap<String,Object>();
					
					transLog.setInsideAppNo(insideAppNo);	// 内审编号
					transLog.setNodeId(nodeId);				// 节点编号
//					transLog.setRspClob(responseStr);		// 响应报文
					transLog.setDuration(cost);				// 交易耗时
					
					transLog.setRequestTimeLong(start);		// 请求开始时间
					
					transLog.setResultFlag(resultFlag);		// 交易结果
					transLog.setReturnCode(responseCode);	// 交易返回码
					
					data.put(OpasConstants.TRANS_CODE, OpasConstants.TRANS_CODE_SAVE2DB);
					data.put(OpasConstants.TRANS_LOG, new String(JsonUtils.jsonFromObject(transLog, OpasConstants.UTF8), OpasConstants.UTF_8));
					
					if (logger.isInfoEnabled()) {
						logger.info(OpasConstants.DEFAULT_JMS_TRANSPORT + "-" + OpasConstants.LOCAL_LOG_REQ + " = " + data);
					}
				} catch (Exception e) {
					logger.error("MqLogInterceptor invoke mq server error!", e);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("MqLogInterceptor调用MQ服务结束");
				}
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
	}
}
