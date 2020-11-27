package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.Education;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.parser.EducationMessageParser;

/**
 * 学历报文操作
 * 
 * @author zhiguo.li
 *
 */
public class EducationMessageOperator extends MessageOperator<Education> {

	private MessageParser<Education> messageParser;

	public EducationMessageOperator() {
		this.messageParser = new EducationMessageParser();
	}

	public MessageParser<Education> getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(MessageParser<Education> messageParser) {
		this.messageParser = messageParser;
	}

	@Override
	public Education operate(String message) throws Exception {
		return getMessageParser().parse(message);
	}

}
