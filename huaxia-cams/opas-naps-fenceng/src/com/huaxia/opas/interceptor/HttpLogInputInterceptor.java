package com.huaxia.opas.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.IcsException;
import com.huateng.neofp.channel.ChannelContext;
import com.huateng.neofp.channel.ChannelInterceptor;
import com.huateng.neofp.core.ContextEx;
import com.huaxia.opas.dao.common.OpasConstants;
import com.huaxia.opas.domain.log.TransLog;

/**
 * 请求拦截器
 * 
 * @author uatym990190
 */
public class HttpLogInputInterceptor implements ChannelInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(HttpLogInputInterceptor.class);

	@Override
	public void onRequest(ChannelContext channelcontext, ContextEx contextex) throws IcsException {
		// 只要开启INFO级别日志就会处理
		if (logger.isErrorEnabled()) {
			String processId = contextex.getProcessId(); // 交易码

			Object requestPayload = channelcontext.getRequestPayload();
			String requestStr = String.valueOf(requestPayload);

			StringBuilder request = new StringBuilder(256);
			request.append("request data:").append(OpasConstants.STR_LEFT).append(processId)
					.append(OpasConstants.STR_RIGHT).append(OpasConstants.STR_LEFT).append(requestStr)
					.append(OpasConstants.STR_RIGHT);

			if (logger.isInfoEnabled()) {
				logger.info(request.toString());
			}

			// 内审编号 从 报文中截取
			String insideAppNo = StringUtils
					.substring(StringUtils.substringBetween(requestStr, "nsideAppNo\":\"", "\""), 0, 32);
			if (StringUtils.isBlank(insideAppNo)) {
				insideAppNo = StringUtils
						.substring(StringUtils.substringBetween(requestStr, "\"processInsNo\":\"", "\""), 0, 32);
			}

			TransLog transLog = new TransLog();

			transLog.setInsideAppNo(insideAppNo); // 内审编号
			// transLog.setReqClob(requestStr); // 请求报文
			transLog.setTransCode(processId); // 交易码

			// transLog.setRequestTime(new
			// java.util.Date(System.currentTimeMillis()));
			// transLog.setTransDate(new
			// java.sql.Date(System.currentTimeMillis())); // 请求时间

			transLog.setTransType(OpasConstants.TRANSTYPE_HTTP_JSON); // 请求类型

			contextex.setAttribute(OpasConstants.X_TRANSLOG_Y, transLog);
		}
	}

	@Override
	public void onResponse(ChannelContext channelcontext, ContextEx contextex, Throwable throwable) {
	}

}
