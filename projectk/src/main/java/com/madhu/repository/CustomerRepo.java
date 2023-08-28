package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByMobileNo(String mobileNumber);

	Optional<Customer> findByEmail(String email);

	List<Customer> findByKeywordsContaining(String keyword);

	Optional<Customer> findByAddressAddressId(Integer addressId);

	List<Customer> findByAddressVillageVillageId(Integer villageId);

	List<Customer> findByAddressVillageVillageName(String villageName);

	List<Customer> findByAddressVillagePincode(Integer pincode);

	List<Customer> findByAgeGreaterThan(int age);

	List<Customer> findByAgeLessThan(int age);

}
