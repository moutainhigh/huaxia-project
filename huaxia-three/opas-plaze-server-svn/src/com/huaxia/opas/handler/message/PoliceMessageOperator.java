package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.Police;
import com.huaxia.opas.parser.PoliceMessageParser;

/**
 * 增强公安报文操作
 * 
 * @author zhiguo.li
 *
 */
public class PoliceMessageOperator {

	private PoliceMessageParser messageParser;

	public PoliceMessageOperator() {
		this.messageParser = new PoliceMessageParser();
	}

	/**
	 * 获取单条公安查询信息
	 *
	 * @param message
	 *            请求信息
	 * @return 公安实体对象
	 * @throws Exception
	 */
	public Police execute(String message) throws Exception {
		return getMessageParser().parse(message);
	}

	public PoliceMessageParser getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(PoliceMessageParser messageParser) {
		this.messageParser = messageParser;
	}

}
