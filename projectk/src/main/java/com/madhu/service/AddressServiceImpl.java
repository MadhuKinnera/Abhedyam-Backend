package com.madhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.Address;
import com.madhu.exception.AddressException;
import com.madhu.repository.AddressRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Address addAddress(Address address) {

		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(Integer addressId, Address address) throws AddressException {

		if (!utils.isAddressExist(addressId))
			throw new AddressException(Constants.ADDRESS_ID_NOT_FOUND + addressId);

		return addressRepo.save(address);

	}

	@Override
	public Address deleteAddress(Integer addressId) throws AddressException {

		Address address = getAddress(addressId);

		addressRepo.delete(address);

		return address;

	}

	@Override
	public Address getAddress(Integer addressId) throws AddressException {

		return addressRepo.findById(addressId)
				.orElseThrow(() -> new AddressException(Constants.ADDRESS_ID_NOT_FOUND + addressId));
	}

	@Override
	public List<Address> getAddressesByVillageName(String villageName) throws AddressException {

		List<Address> addresses = addressRepo.findByVillageVillageName(villageName);

		if (addresses.isEmpty())
			throw new AddressException(Constants.ADDRESSES_NOT_FOUND);

		return addresses;
	}

	@Override
	public List<Address> getAddressesByVillageId(Integer villageId) throws AddressException {
		List<Address> addresses = addressRepo.findByVillageVillageId(villageId);

		if (addresses.isEmpty())
			throw new AddressException(Constants.ADDRESSES_NOT_FOUND);

		return addresses;
	}

}
