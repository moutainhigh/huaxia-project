package com.huaxia.opas.handler.message;

import java.util.List;

import com.huaxia.opas.domain.ZMXYIvsScore;
import com.huaxia.opas.domain.ZMXYWatchList2;
import com.huaxia.opas.domain.ZMXYScore;
import com.huaxia.opas.parser.ZMXYMessageParser;

/**
 * 芝麻信用报文操作
 * 
 * @author zhiguo.li
 *
 */
public class ZMXYMessageOperator {

	private ZMXYMessageParser messageParser;

	public ZMXYMessageOperator() {
		this.messageParser = new ZMXYMessageParser();
	}

	public ZMXYIvsScore executeIvsScore(String message) {
		return getMessageParser().parseMessageOfIvsScore(message);
	}

	public ZMXYScore executeScore(String message) {
		return getMessageParser().parseMessageOfScore(message);
	}

	public List<ZMXYWatchList2> executeWatchList2(String message, String appId) {
		return getMessageParser().parseMessageOfWatchList2(message, appId);
	}

	public ZMXYMessageParser getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(ZMXYMessageParser messageParser) {
		this.messageParser = messageParser;
	}

}
