package com.madhu.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Remainder;

public interface RemainderRepo extends JpaRepository<Remainder, Integer> {
	
	
	List<Remainder> findBySaleRecordRecordId(Integer recordId);
	
	List<Remainder> findBySaleRecordCustomerUserUserId(Integer userId);
	
	List<Remainder> findBySaleRecordCustomerCustomerId(Integer customerId);
	
	List<Remainder> findByRemainderDateBetweenAndSaleRecordCustomerUserUserId(LocalDate startDate,LocalDate endDate, Integer userId);
	
	
	

}
