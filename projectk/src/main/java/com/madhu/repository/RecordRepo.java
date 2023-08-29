package com.madhu.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;

public interface RecordRepo extends JpaRepository<SaleRecord, Integer> {

	List<SaleRecord> findByCustomerCustomerId(Integer customerId);

	List<SaleRecord> findByAddressVillageVillageId(Integer villageId);

	List<SaleRecord> findByAddressVillageVillageName(String villageName);

	List<SaleRecord> findByAddressVillageMandal(String mandal);

	List<SaleRecord> findByAddressVillageDistrict(String district);

	List<SaleRecord> findByAddressVillagePincode(Integer pincode);
	
	List<SaleRecord> findByProductProductId(Integer productId);
	
	List<SaleRecord> findByProductProductName(String productName);
	
	List<SaleRecord> findByCustomerEmail(String email);
	
	List<SaleRecord> findByCustomerCustomerName(String name);
	
	List<SaleRecord> findByStartDateBetween(LocalDate fromDate , LocalDate toTime);
	
	List<SaleRecord> findByEndDateBetween(LocalDate fromDate , LocalDate toTime);
	
	

}
