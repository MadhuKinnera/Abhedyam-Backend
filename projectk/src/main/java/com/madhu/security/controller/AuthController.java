package com.madhu.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.JwtResponse;
import com.madhu.entity.User;
import com.madhu.exception.UserException;
import com.madhu.repository.UserRepo;
import com.madhu.security.dto.LoginRequest;
import com.madhu.security.service.JwtAuthProvider;
import com.madhu.security.service.LoadUserImpl;
import com.madhu.utils.CommonUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtAuthProvider jwtAuthProvider;

	@Autowired
	private LoadUserImpl loadUser;

	@Autowired
	private CommonUtils utils;

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public JwtResponse loginHandler(@io.swagger.v3.oas.annotations.parameters.RequestBody LoginRequest request) throws UserException {

		String email = request.getEmail();

		UserDetails userDetails = loadUser.loadUserByUsername(email);

		System.out.println("user loaded " + userDetails);

		if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
			return new JwtResponse("Password Not Matched try madhu");
		}

		User user = uRepo.findByEmail(email)
				.orElseThrow(() -> new UserException("User Not Found with Email " + email));

		utils.userId = user.getUserId();
		
		System.out.println("generating jwt token");

		String jwt = jwtAuthProvider.generateToken(email);

		return new JwtResponse(jwt);
	}

	@GetMapping("/hello")
	public JwtResponse hello() {
		return new JwtResponse("Hello Madhu");
	}

}
