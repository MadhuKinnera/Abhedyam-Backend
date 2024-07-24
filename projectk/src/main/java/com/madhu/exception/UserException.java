package com.madhu.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserException extends Exception {
    private String msg;
}
