package com.huaxia.opas.handler.message;

import com.huaxia.opas.domain.QHZXMsc8005;
import com.huaxia.opas.domain.QHZXMsc8007;
import com.huaxia.opas.domain.QHZXMsc8036;
import com.huaxia.opas.domain.QHZXMsc8037;
import com.huaxia.opas.domain.QHZXMsc8075;
import com.huaxia.opas.domain.QHZXMsc8107;
import com.huaxia.opas.parser.QHZXMessageParser;

/**
 * 前海征信报文操作
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMessageOperator {

	private QHZXMessageParser messageParser;

	public QHZXMessageOperator() {
		this.messageParser = new QHZXMessageParser();
	}

	public QHZXMessageParser getMessageParser() {
		return messageParser;
	}

	public void setMessageParser(QHZXMessageParser messageParser) {
		this.messageParser = messageParser;
	}

	public QHZXMsc8005 executeMsc8005(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8005(message);
	}

	public QHZXMsc8007 executeMsc8007(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8007(message);
	}

	public QHZXMsc8036 executeMsc8036(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8036(message);
	}

	public QHZXMsc8037 executeMsc8037(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8037(message);
	}

	public QHZXMsc8075 executeMsc8075(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8075(message);
	}

	public QHZXMsc8107 executeMsc8107(String message) throws Exception {
		return getMessageParser().parseMessageOfMsc8107(message);
	}

}
