package com.madhu.utils;

import org.springframework.stereotype.Component;

import com.madhu.entity.User;

import lombok.Data;

@Data
@Component
public class UserInfo {
	
	private User user;
	
	private Integer userId;

}
