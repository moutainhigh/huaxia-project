package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.PBOC;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.parser.PBOCMessageParser;

/**
 * 人行报文操作
 * 
 * @author zhiguo.li
 *
 */
public class PBOCMessageOperator extends MessageOperator<PBOC> {

	private MessageParser<PBOC> messageParser;

	public PBOCMessageOperator() {
		this.messageParser = new PBOCMessageParser();
	}
	
	public MessageParser<PBOC> getMessageParser() {
		return messageParser;
	}
	
	public void setMessageParser(MessageParser<PBOC> messageParser) {
		this.messageParser = messageParser;
	}

	@Override
	public PBOC operate(String message) throws Exception {
		return getMessageParser().parse(message);
	}

}
