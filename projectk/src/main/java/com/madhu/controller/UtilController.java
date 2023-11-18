package com.madhu.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.JwtResponse;
import com.madhu.utils.CommonUtils;

@RestController
@RequestMapping("/cloudinary")
public class UtilController {

	@Autowired
	private CommonUtils utils;

	@PostMapping("/uploadImage")
	public ResponseEntity<JwtResponse> uploadImageToCloudinary(@RequestParam("image") MultipartFile file)
			throws IOException {
		

		System.out.println("Inside Upload Image ");

		String res = utils.convertImageToUrl(file);

		return ResponseEntity.ok(new JwtResponse(res));

	}

}
