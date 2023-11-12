package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.dto.UserDTO;
import com.madhu.exception.UserException;
import com.madhu.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	

	@PostMapping("/addUser")
	ResponseEntity<GeneralResponse> addUser(@RequestBody UserDTO dto) throws Exception {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("User Added with Email "+dto.getEmail());
		generalResponse.setData(userService.addUser(dto));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteUser/{userId}")
	ResponseEntity<GeneralResponse> deleteUser(@PathVariable Integer userId) throws UserException {
		
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("User Deleted Successfully with User Id "+userId);
		generalResponse.setData(userService.deleteUser(userId));

		return ResponseEntity.ok(generalResponse);

	}

	@PutMapping("/updatePassword/{userId}")
	ResponseEntity<GeneralResponse> updatePassword(@PathVariable Integer userId,@RequestParam String password) throws UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("User Password Updated ");
		generalResponse.setData(userService.updatePassword(userId, password));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getUser/{userId}")
	ResponseEntity<GeneralResponse> getUserById(Integer userId) throws UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("User Found with User Id "+userId);
		generalResponse.setData(userService.getUserById(userId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getUsers")
	ResponseEntity<GeneralResponse> getAllUsers() throws UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Users Found ");
		generalResponse.setData(userService.getAllUsers());

		return ResponseEntity.ok(generalResponse);
	}

}
