package com.huaxia.opas.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.IcsException;
import com.huateng.neofp.channel.ChannelContext;
import com.huateng.neofp.channel.ChannelInterceptor;
import com.huateng.neofp.core.ContextEx;
import com.huaxia.opas.dao.common.OpasConstants;
import com.huaxia.opas.domain.log.TransLog;

/**
 * MQ请求报文拦截器
 * 
 * @author uatym990190
 */
@SuppressWarnings("rawtypes")
public class MqLogInputInterceptor implements ChannelInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MqLogInputInterceptor.class);

	@Override
	public void onRequest(ChannelContext channelcontext, ContextEx contextex) throws IcsException {

		// 只要开启INFO级别日志就会处理
		if (logger.isErrorEnabled()) {
			String requestId = contextex.getRequestId();
			String processId = contextex.getProcessId();

			Object requestPayload = channelcontext.getRequestPayload();
			String requestStr = String.valueOf(requestPayload);

			StringBuilder request = new StringBuilder(256);
			request.append("request data:").append(OpasConstants.STR_LEFT).append(requestId)
					.append(OpasConstants.STR_RIGHT).append(OpasConstants.STR_LEFT).append(processId)
					.append(OpasConstants.STR_RIGHT).append(OpasConstants.STR_LEFT).append(requestStr)
					.append(OpasConstants.STR_RIGHT);

			if (logger.isInfoEnabled()) {
				logger.info(requestStr);
			}

			// 调用MQ存储日志信息
			TransLog transLog = new TransLog();

			// 内审编号 从 报文中截取 MQ,拦截器拦不到
			// String insideAppNo =
			// StringUtils.substring(StringUtils.substringBetween(requestStr,
			// "\"insideAppNo\":\"", "\""), 0, 32);

			transLog.setRequestId(requestId);
			// transLog.setInsideAppNo(insideAppNo);
			// transLog.setReqClob(requestStr);
			transLog.setTransCode(processId);
			// transLog.setTransDate(new
			// java.sql.Date(System.currentTimeMillis()));
			transLog.setTransType(OpasConstants.TRANSTYPE_MQ_JSON);

			contextex.setAttribute(OpasConstants.X_TRANSLOG_MQ_Y, transLog);
		}
	}

	@Override
	public void onResponse(ChannelContext channelcontext, ContextEx contextex, Throwable throwable) {
	}

}
