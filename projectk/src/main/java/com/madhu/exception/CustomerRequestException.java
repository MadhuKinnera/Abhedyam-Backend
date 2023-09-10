package com.madhu.exception;

public class CustomerRequestException  extends Exception{
	

	private static final long serialVersionUID = 495080092093495378L;

	public CustomerRequestException() {
	}
	
	public CustomerRequestException(String msg) {
		super(msg);
	}
	

}
