package com.madhu.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDTO {
	
	
	private String customerName;
	private String profileImage;
	private Integer age;
	private String profession;
	private String mobileNo;
	private String email;
	private String description;
	
	
	private AddressDTO addressDto;

	private List<String> keywords = new ArrayList<>();

}
