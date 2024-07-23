package com.madhu.service;

import com.madhu.dto.AddressDTO;
import com.madhu.dto.CustomerDTO;
import com.madhu.dto.CustomerPersonalDto;
import com.madhu.dto.CustomerResponseModel;
import com.madhu.dto.NameAndId;
import com.madhu.dto.PlainCustomer;
import com.madhu.entity.Customer;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

	Customer addCustomer(CustomerDTO dto) throws CustomerException, UserException, IOException;

	Customer getCustomerById(Integer customerId) throws CustomerException, UserException;

	Customer updateCustomer(Integer customerId, Customer customer) throws CustomerException, UserException;

	Customer updateAddressToACustomer(AddressDTO dto, Integer customerId) throws CustomerException, UserException;

	Customer deleteCustomerById(Integer customerId) throws CustomerException, UserException;

	List<PlainCustomer> getPlainCustomers() throws UserException;

	Customer updateProfilePicture(Integer customerId, String file)
			throws IOException, CustomerException, UserException;

	Customer getCustomerByPhoneNumber(String phoneNumber) throws CustomerException, UserException;

	Customer getCustomerByEmail(String email) throws CustomerException, UserException;

	Customer getCustomersByAddressId(Integer addressId) throws CustomerException, AddressException, UserException;

	List<Customer> getCustomersByAddressVillageId(Integer villageId)
			throws CustomerException, VillageException, UserException;

	List<Customer> getCustomersByAddressVillageName(String villageName)
			throws CustomerException, VillageException, UserException;

	List<Customer> getCustomersByAddressPincode(Integer pincode)
			throws CustomerException, VillageException, UserException;

	List<CustomerResponseModel> getCustomersByRank() throws CustomerException, UserException;

	List<CustomerResponseModel> getCustomersByCustomerNameContaining(String customerName)
			throws CustomerException, UserException;

	CustomerResponseModel getCustomerResponseModelByCustomerId(Integer customerId)
			throws UserException, CustomerException;

	List<NameAndId> getCustomersName() throws CustomerException, UserException;

	CustomerPersonalDto getCustomerPersonalDetails(String customerCode) throws CustomerException;

}
