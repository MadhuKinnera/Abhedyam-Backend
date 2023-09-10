package com.madhu.service;

import java.util.List;

import com.madhu.dto.UserDTO;
import com.madhu.entity.User;
import com.madhu.exception.UserException;

public interface UserService {
	
	User addUser(UserDTO dto) throws Exception ;
	
	User deleteUser(Integer userId) throws UserException;
	
	User updatePassword(Integer userId,String password) throws UserException;
	
	User getUserById(Integer userId) throws UserException;
	
	List<User> getAllUsers() throws UserException;

}
