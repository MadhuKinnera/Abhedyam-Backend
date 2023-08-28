package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Product;
import com.madhu.exception.ProductException;
import com.madhu.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	ResponseEntity<GeneralResponse> addProductHandler(@RequestBody Product product) throws ProductException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Added ");
		generalResponse.setData(productService.addProduct(product));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProduct/{productId}")
	ResponseEntity<GeneralResponse> getProductByProductIdHandler(@PathVariable Integer productId)
			throws ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Found By Product Id ");
		generalResponse.setData(productService.getProductByProductId(productId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateProduct/{productId}")
	ResponseEntity<GeneralResponse> updateProductHandler(@PathVariable Integer productId, @RequestBody Product product)
			throws ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Updated with Product Id " + productId);
		generalResponse.setData(productService.updateProduct(productId, product));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteProduct/{productId}")
	ResponseEntity<GeneralResponse> deleteProduct(@PathVariable Integer productId) throws ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Deleted with Product Id " + productId);
		generalResponse.setData(productService.deleteProduct(productId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProductByName/{productName}")
	ResponseEntity<GeneralResponse> getProductByNameHandler(@PathVariable String productName) throws ProductException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Found with Product Name " + productName);
		generalResponse.setData(productService.getProductByName(productName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProductByRank")
	ResponseEntity<GeneralResponse> getProductByRankHandler() {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Products Found By Ranks ");
		generalResponse.setData(productService.getProductByRank());

		return ResponseEntity.ok(generalResponse);

	}

}
