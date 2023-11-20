package com.madhu.dto;

import lombok.Data;

@Data
public class UserDTO {

	private String email;
	private String password;
	private String fullName;
	
	private String phoneNumber;
	
	private String qrImageUrl;
	
	private String profileImageUrl;
}
