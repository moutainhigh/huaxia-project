package com.huaxia.opas.handler.message;

public abstract class MessageOperator<T> {
	
	public abstract T operate(String message) throws Exception;
	
}
