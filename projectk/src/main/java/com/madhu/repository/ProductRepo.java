package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	
	Optional<Product> findByProductNameAndUserUserId(String productName,Integer userId);
	 	
	List<Product> findBySaleRecordsCustomerAddressVillageVillageIdAndUserUserId(Integer villageId , Integer userId );
	
	List<Product> findByUserUserId(Integer userId);
	
	Optional<Product> findByProductIdAndUserUserId(Integer productId,Integer userId);
	
	List<Product> findByProductNameContainingAndUserUserId(String productName,Integer userId);
}

