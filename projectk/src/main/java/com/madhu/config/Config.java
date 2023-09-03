package com.madhu.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class Config {

	@Bean
	Cloudinary getCloudinary() {

		Map<String, String> map = new HashMap<>();

		map.put("cloud_name", "dohsebpd1");
		map.put("api_key", "421394291995232");
		map.put("api_secret", "ChXQSdHuz9jeycoA0XsW2BFwkcs");
		map.put("secure", "true");

		return new Cloudinary(map);
	}

}
