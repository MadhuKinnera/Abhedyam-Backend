package com.madhu.service;

import java.util.List;

import com.madhu.entity.Address;
import com.madhu.exception.AddressException;

public interface AddressService { 
	
	
	Address addAddress(Address address);
	
	Address updateAddress(Integer addressId,Address address) throws AddressException;
	
	Address deleteAddress(Integer id) throws AddressException;
	
	Address getAddress(Integer id) throws AddressException;
		
	List<Address> getAddressesByVillageName(String villageName) throws AddressException;
	
	List<Address> getAddressesByVillageId(Integer villageId) throws AddressException;
	
	
	

}
