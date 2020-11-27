package com.huaxia.plaze.exception;

public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 8067858901422751311L;
	
	public String message;
	
	public ApplicationException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
