package com.madhu.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionException extends Exception {
	private String msg;
	
}
