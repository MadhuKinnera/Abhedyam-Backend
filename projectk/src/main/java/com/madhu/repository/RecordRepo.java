package com.madhu.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.SaleRecord;

public interface RecordRepo extends JpaRepository<SaleRecord, Integer> {

	List<SaleRecord> findByCustomerCustomerIdAndCustomerUserUserId(Integer customerId, Integer userId);

	List<SaleRecord> findByCustomerAddressVillageVillageIdAndCustomerUserUserId(Integer villageId, Integer userId);

	List<SaleRecord> findByCustomerAddressVillageVillageNameAndCustomerUserUserId(String villageName, Integer userId);

	List<SaleRecord> findByCustomerAddressVillageMandalAndCustomerUserUserId(String mandal, Integer userId);

	List<SaleRecord> findByCustomerAddressVillageDistrictAndCustomerUserUserId(String district, Integer userId);

	List<SaleRecord> findByCustomerAddressVillagePincodeAndCustomerUserUserId(Integer pincode, Integer userId);

	List<SaleRecord> findByProductProductIdAndCustomerUserUserId(Integer productId, Integer userId);

	List<SaleRecord> findByProductProductNameAndCustomerUserUserId(String productName, Integer userId);

	List<SaleRecord> findByCustomerEmailAndCustomerUserUserId(String email, Integer userId);

	List<SaleRecord> findByCustomerCustomerNameAndCustomerUserUserId(String name, Integer userId);

	List<SaleRecord> findByStartDateBetweenAndCustomerUserUserId(LocalDate fromDate, LocalDate toTime, Integer userId);

	List<SaleRecord> findByEndDateBetweenAndCustomerUserUserId(LocalDate fromDate, LocalDate toTime, Integer userId);

	List<SaleRecord> findByCustomerUserUserId(Integer userId);

	List<SaleRecord> findByCustomerUserUserIdAndRecordIdOrCustomerCustomerNameIgnoreCaseContainingOrProductProductNameIgnoreCaseContaining(
			Integer userId, Integer recordId, String customerName, String productName);

	Optional<SaleRecord> findByCustomerUserUserIdAndRecordId(Integer userId, Integer recordId);

	List<SaleRecord> findByCustomerCustomerId(Integer customerId);

}
