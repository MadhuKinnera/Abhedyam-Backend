package com.madhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.madhu.dto.UserDTO;
import com.madhu.entity.User;
import com.madhu.exception.UserException;
import com.madhu.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public User addUser(UserDTO dto) throws Exception {

		var user = new User();

		user.setEmail(dto.getEmail());
		user.setFullName(dto.getFullName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setPhoneNumber(dto.getPhoneNumber());
		user.setProfileImageUrl(dto.getProfileImageUrl());
		user.setQrImageUrl(dto.getQrImageUrl());

		return userRepo.save(user);

	}

	@Override
	public User deleteUser(Integer userId) throws UserException {
		User user = getUserById(userId);

		userRepo.delete(user);
		return user;
	}

	@Override
	public User updatePassword(Integer userId, String password) throws UserException {

		User user = getUserById(userId);

		user.setPassword(password);
		return userRepo.save(user);
	}

	@Override
	public User getUserById(Integer userId) throws UserException {

		return userRepo.findById(userId).orElseThrow(() -> new UserException("User Not Found with User Id " + userId));
	}

	@Override
	public List<User> getAllUsers() throws UserException {

		List<User> users = userRepo.findAll();

		if (users.isEmpty())
			throw new UserException("No Users Found ");

		return users;

	}

}
