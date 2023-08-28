package com.madhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
	
	
	List<Address> findByVillageVillageId(Integer villageId);
	
	List<Address> findByVillageVillageName(String villageName);

}
