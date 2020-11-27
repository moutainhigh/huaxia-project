package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.BRZX;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.parser.BRZXMessageParser;

/**
 * 百融报文操作
 * 
 * @author zhiguo.li
 *
 */
public class BRZXMessageOperator extends MessageOperator<BRZX> {

	private MessageParser<BRZX> messageParser;

	public BRZXMessageOperator() {
		this.messageParser = new BRZXMessageParser();
	}
	
	@Override
	public BRZX operate(String message) throws Exception {
		return getMessageParser().parse(message);
	}
	
	public MessageParser<BRZX> getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(MessageParser<BRZX> messageParser) {
		this.messageParser = messageParser;
	}

}
