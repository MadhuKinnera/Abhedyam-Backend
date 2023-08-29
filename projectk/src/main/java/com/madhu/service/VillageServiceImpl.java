package com.madhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.Address;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.VillageException;
import com.madhu.repository.VillageRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageRepo villageRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Village addVillage(Village village) throws VillageException {

		return villageRepo.save(village);
	}

	@Override
	public Village getVillageByVillageId(Integer villageId) throws VillageException {

		return villageRepo.findById(villageId)
				.orElseThrow(() -> new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId));
	}

	@Override
	public Village updateVillage(Integer villageId, Village village) throws VillageException {
		if (!utils.isVillageExist(villageId))
			throw new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId);

		village.setVillageId(villageId);

		return villageRepo.save(village);
	}

	@Override
	public Village deleteVillage(Integer villageId) throws VillageException {

		Village village = getVillageByVillageId(villageId);

		villageRepo.delete(village);

		return village;
	}

	@Override
	public List<Village> getVillagesByRank() throws VillageException {

		List<Village> villages = villageRepo.findAll();

		if (villages.isEmpty())
			throw new VillageException("No Villages Found ");

		return villages;
	}

	@Override
	public List<Address> getAddressByVillageId(Integer villageId) throws VillageException, AddressException {

		Village village = getVillageByVillageId(villageId);

		if (village.getAddresses().isEmpty())
			throw new AddressException(" No Addresses Found with Village Id " + villageId);

		return village.getAddresses();
	}

	@Override
	public Village getVillageByCustomerName(String customerName) throws CustomerException, VillageException {

		return villageRepo.findTopByAddressesInCustomerCustomerName(customerName)
				.orElseThrow(() -> new CustomerException(" Village Not Found with the Customer Name " + customerName));
	}

	@Override
	public List<Village> getVillagesByPincode(Integer pincode) throws VillageException {

		List<Village> villages = villageRepo.findByPincode(pincode);

		if (villages.isEmpty())
			throw new VillageException("Villages Not Found with Pincode " + pincode);

		return villages;

	}

}
