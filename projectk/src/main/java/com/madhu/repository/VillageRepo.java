package com.madhu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Village;

public interface VillageRepo extends JpaRepository<Village, Integer> {
	
	
	Optional<Village> findByVillageName(String villageName);
	
	Optional<Village> findByPincode(Integer pincode);

}
