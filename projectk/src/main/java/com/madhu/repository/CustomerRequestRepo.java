package com.madhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.CustomerRequest;

public interface CustomerRequestRepo extends JpaRepository<CustomerRequest, Integer> {
	
	
	List<CustomerRequest> findByUserUserId(Integer userId);

}
