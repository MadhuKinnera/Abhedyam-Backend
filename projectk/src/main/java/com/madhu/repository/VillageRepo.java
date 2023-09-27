package com.madhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Village;

public interface VillageRepo extends JpaRepository<Village, Integer> {

	Optional<Village> findByVillageNameAndUserUserId(String villageName, Integer userId);

	Optional<Village> findByAddressesCustomerCustomerIdAndUserUserId(Integer customerId, Integer userId);

	Optional<Village> findTopByAddressesCustomerCustomerNameAndUserUserId(String customerName, Integer userId);

	List<Village> findByPincodeAndUserUserId(Integer pincode, Integer userId);

	List<Village> findByUserUserId(Integer userId);

	Optional<Village> findByVillageIdAndUserUserId(Integer villageId, Integer userId);

	List<Village> findByVillageNameContainingAndUserUserId(String villageName,Integer userId);
}
