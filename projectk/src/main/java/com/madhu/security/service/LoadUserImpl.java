package com.madhu.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.madhu.entity.User;
import com.madhu.repository.UserRepo;
import com.madhu.utils.UserInfo;

@Service
public class LoadUserImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserInfo userInfo;
	

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with Email " + email));

		System.out.println("user found " + user);

		userInfo.setUser(user);
		userInfo.setUserId(user.getUserId());
		
	
		List<GrantedAuthority> authorities = new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
	}

}
