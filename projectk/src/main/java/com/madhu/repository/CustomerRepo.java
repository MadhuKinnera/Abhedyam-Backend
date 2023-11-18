package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByMobileNoAndUserUserId(String mobileNumber, Integer userId);

	Optional<Customer> findByEmailAndUserUserId(String email, Integer userId);

	List<Customer> findByKeywordsContainingAndUserUserId(String keyword, Integer UserId);

	Optional<Customer> findByAddressAddressIdAndUserUserId(Integer addressId, Integer userId);

	List<Customer> findByAddressVillageVillageIdAndUserUserId(Integer villageId, Integer userId);

	List<Customer> findByAddressVillageVillageNameAndUserUserId(String villageName, Integer userId);

	List<Customer> findByAddressVillagePincodeAndUserUserId(Integer pincode, Integer userId);

	List<Customer> findByAgeGreaterThanAndUserUserId(int age, Integer userId);

	List<Customer> findByAgeLessThanAndUserUserId(int age, Integer userId);

	List<Customer> findByUserUserId(Integer userId);

	Optional<Customer> findByCustomerIdAndUserUserId(Integer customerId, Integer userId);

	List<Customer> findByCustomerNameContainingAndUserUserId(String customerName, Integer userId);

	Optional<Customer> findByCustomerCode(String customerCode);
}
