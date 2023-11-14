package com.madhu.service;

import java.util.List;

import com.madhu.dto.NameAndId;
import com.madhu.dto.VillageDTO;
import com.madhu.dto.VillageResponseModel;
import com.madhu.entity.Address;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;

public interface VillageService {

	Village addVillage(VillageDTO village) throws VillageException, UserException;

	Village getVillageByVillageId(Integer villageId) throws VillageException;

	Village updateVillage(Integer villageId, Village village) throws VillageException;

	Village deleteVillage(Integer villageId) throws VillageException;

	List<Village> getVillagesByRank() throws VillageException;

	List<Address> getAddressByVillageId(Integer villageId) throws VillageException, AddressException;

	Village getVillageByCustomerId(Integer customerId) throws CustomerException, VillageException;

	Village getVillageByCustomerName(String customerName) throws CustomerException, VillageException;

	List<Village> getVillagesByPincode(Integer pincode) throws VillageException;

	List<VillageResponseModel> getVillageWiseData() throws VillageException;

	VillageResponseModel getVillageWiseDataByVillageId(Integer villageId) throws VillageException;

	List<VillageResponseModel> getVillageWiseDataByVillageNameContaining(String villageName) throws VillageException;

	List<NameAndId> getVillageNames() throws VillageException;
	
}
