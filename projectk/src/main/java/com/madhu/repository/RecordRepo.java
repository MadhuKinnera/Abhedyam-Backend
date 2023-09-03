package com.madhu.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.SaleRecord;

public interface RecordRepo extends JpaRepository<SaleRecord, Integer> {

	List<SaleRecord> findByCustomerCustomerId(Integer customerId);

	List<SaleRecord> findByCustomerAddressVillageVillageId(Integer villageId);

	List<SaleRecord> findByCustomerAddressVillageVillageName(String villageName);

	List<SaleRecord> findByCustomerAddressVillageMandal(String mandal);

	List<SaleRecord> findByCustomerAddressVillageDistrict(String district);

	List<SaleRecord> findByCustomerAddressVillagePincode(Integer pincode);
	
	List<SaleRecord> findByProductProductId(Integer productId);
	
	List<SaleRecord> findByProductProductName(String productName);
	
	List<SaleRecord> findByCustomerEmail(String email);
	
	List<SaleRecord> findByCustomerCustomerName(String name);
	
	List<SaleRecord> findByStartDateBetween(LocalDate fromDate , LocalDate toTime);
	
	List<SaleRecord> findByEndDateBetween(LocalDate fromDate , LocalDate toTime);
	
	

}
