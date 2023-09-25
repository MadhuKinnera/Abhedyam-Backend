package com.madhu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.ProductDTO;
import com.madhu.dto.ProductResponseModel;
import com.madhu.entity.Product;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;

public interface ProductService {

	Product addProduct(ProductDTO dto) throws ProductException,UserException;

	Product getProductByProductId(Integer productId) throws ProductException;

	Product updateProduct(Integer productId,Product product) throws ProductException;

	Product deleteProduct(Integer productId) throws ProductException;
	 
	Product getProductByName(String productName) throws ProductException;
	
	Product uploadProductImage(Integer productId,MultipartFile productFile) throws ProductException, IOException;
	
	List<ProductResponseModel> getProductResponseModels() throws VillageException,ProductException;  
	
	ProductResponseModel getProductResponseModelByProductId(Integer productId) throws VillageException,ProductException;  

}
