package com.madhu.exception;

public class CustomerException extends Exception {
	private static final long serialVersionUID = 7448083384216763386L;

	public CustomerException() {
	}

	public CustomerException(String msg) {
		super(msg);
	}

}
