package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.fico.Fico;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.parser.FicoMessageParser;

public class FicoMessageOperator extends MessageOperator<Fico>  {
	
	private MessageParser<Fico> messageParser;
	
	
	public FicoMessageOperator() {
		this.messageParser = new FicoMessageParser();
	}

	public MessageParser<Fico> getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(MessageParser<Fico> messageParser) {
		this.messageParser = messageParser;
	}

	@Override
	public Fico operate(String message) throws Exception {
		return getMessageParser().parse(message);
	}


}
