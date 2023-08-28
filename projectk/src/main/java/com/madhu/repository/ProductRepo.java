package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Product;
import com.madhu.exception.ProductException;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	
	Optional<Product> findByProductName(String productName);
	
	
	List<Product> findBySaleRecordsAddressVillageVillageId(Integer villageId );
	
}

