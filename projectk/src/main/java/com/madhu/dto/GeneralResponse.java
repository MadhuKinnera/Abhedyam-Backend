package com.madhu.dto;

import lombok.Data;

@Data
public class GeneralResponse {

	private Integer status=200;

	private String message;

	private Object data;
	

}
