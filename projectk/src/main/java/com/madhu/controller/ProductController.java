package com.madhu.controller;

import java.io.IOException;

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
import com.madhu.dto.ProductDTO;
import com.madhu.entity.Product;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	ResponseEntity<GeneralResponse> addProductHandler(@RequestBody ProductDTO product)
			throws ProductException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Added ");
		generalResponse.setData(productService.addProduct(product));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProductByProductId/{productId}")
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

	@GetMapping("/getProducts")
	ResponseEntity<GeneralResponse> getProducts() throws ProductException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Products Found ");
		generalResponse.setData(productService.getProducts());

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getProductResponseModels")
	ResponseEntity<GeneralResponse> getProductResponseModelsHandler() throws VillageException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Response Models Found By Ranks ");
		generalResponse.setData(productService.getProductResponseModels());

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getProductResponseModelByRank/{productId}")
	ResponseEntity<GeneralResponse> getProductResponseModelByProductId(@PathVariable Integer productId)
			throws VillageException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Response Model Found By Ranks with Product Id " + productId);
		generalResponse.setData(productService.getProductResponseModelByProductId(productId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getProductContainingProductName/{productName}")
	ResponseEntity<GeneralResponse> getProductsContainingProductName(@PathVariable String productName)
			throws ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Products Found that Contain " + productName);
		generalResponse.setData(productService.getProductsContainingProductName(productName));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/uploadProductImage/{productId}")
	ResponseEntity<GeneralResponse> uploadProductImage(@PathVariable Integer productId,
			@RequestParam("image") String productFile) throws ProductException, IOException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Image Updated ");
		generalResponse.setData(productService.uploadProductImage(productId, productFile));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProductsName")
	ResponseEntity<GeneralResponse> getProductsName() throws ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Products Name Found");
		generalResponse.setData(productService.getProductsName());

		return ResponseEntity.ok(generalResponse);
	}

}
