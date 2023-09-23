package com.madhu.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.security.dto.LoginRequest;
import com.madhu.security.service.JwtAuthProvider;
import com.madhu.security.service.LoadUserImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {


	@Autowired
	private JwtAuthProvider jwtAuthProvider;

	@Autowired
	private LoadUserImpl loadUser;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginHandler(@RequestBody LoginRequest request) {

		String email = request.getEmail();

		UserDetails userDetails = loadUser.loadUserByUsername(email);
		
		System.out.println("user loaded "+userDetails);

		if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
			return "Password Not Matched try madhu";
		}
		

		System.out.println("generating jwt token");
		
		String jwt = jwtAuthProvider.generateToken(email);

		return jwt;
	}


	@GetMapping("/hello")
	public String hello() {
		return "Hello Madhu ";
	}

}
