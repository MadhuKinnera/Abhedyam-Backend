package com.madhu.dto;

import com.madhu.enums.Color;

import lombok.Data;

@Data
public class PlainCustomer {
	
	private Integer customerId;
	private String customerName;
	private String customerCode;
	private Integer age;
	private String profession;
	private String mobileNo;
	private String email;
	private String profileImageUrl;
	private String description;
	private Color flag;

}
