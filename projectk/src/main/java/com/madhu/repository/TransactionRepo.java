package com.madhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	
	List<Transaction> findBySaleRecordCustomerCustomerId(Integer customerId);

}
