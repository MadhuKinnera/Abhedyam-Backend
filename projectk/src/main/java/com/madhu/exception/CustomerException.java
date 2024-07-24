package com.madhu.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerException extends Exception {
    private String msg;
}
