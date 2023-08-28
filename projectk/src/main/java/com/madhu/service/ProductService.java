package com.madhu.service;

import java.util.List;

import com.madhu.dto.ProductResponseModel;
import com.madhu.entity.Product;
import com.madhu.exception.ProductException;
import com.madhu.exception.VillageException;

public interface ProductService {

	Product addProduct(Product product) throws ProductException;

	Product getProductByProductId(Integer productId) throws ProductException;

	Product updateProduct(Integer productId,Product product) throws ProductException;

	Product deleteProduct(Integer productId) throws ProductException;
	 
	Product getProductByName(String productName) throws ProductException;
	
	List<ProductResponseModel> getProductByRank() throws VillageException;  
	
	

}
