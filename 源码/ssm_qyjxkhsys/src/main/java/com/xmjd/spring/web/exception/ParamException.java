package com.xmjd.spring.web.exception;

public class ParamException extends RuntimeException {

	public ParamException() {
		super();
	}

	public ParamException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ParamException(String arg0) {
		super(arg0);
	}

	public ParamException(Throwable arg0) {
		super(arg0);
	}
	
}
