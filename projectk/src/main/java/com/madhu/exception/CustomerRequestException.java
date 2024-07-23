package com.madhu.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerRequestException extends Exception {
    private String msg;
}
