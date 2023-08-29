package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Village;

public interface VillageRepo extends JpaRepository<Village, Integer> {

	Optional<Village> findByVillageName(String villageName);

	Optional<Village> findByAddressesInCustomerCustomerId(Integer customerId);

	Optional<Village> findTopByAddressesInCustomerCustomerName(String customerName);

	List<Village> findByPincode(Integer pincode);

}
