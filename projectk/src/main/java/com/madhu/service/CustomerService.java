package com.madhu.service;

import java.io.IOException;
import java.util.List;

import com.madhu.dto.AddressDTO;
import com.madhu.dto.CustomerDTO;
import com.madhu.dto.CustomerPersonalDto;
import com.madhu.dto.CustomerResponseModel;
import com.madhu.dto.FirstCustomerDTO;
import com.madhu.dto.NameAndId;
import com.madhu.dto.PlainCustomer;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.enums.Color;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;

public interface CustomerService {

	Customer addCustomer(CustomerDTO dto) throws CustomerException, UserException, IOException;

	Customer getCustomerById(Integer customerId) throws CustomerException, UserException;

	Customer updateCustomer(Integer customerId, Customer customer) throws CustomerException, UserException;

	Customer updateAddressToACustomer(AddressDTO dto, Integer customerId) throws CustomerException, UserException;

	Customer updateFlagOfACustomer(Integer customerId, Color flag) throws CustomerException, UserException;

	Customer deleteCustomerById(Integer customerId) throws CustomerException, UserException;

	List<PlainCustomer> getPlainCustomers() throws UserException;

	Customer addKeywordsToCustomer(Integer customerId, List<String> keywords) throws CustomerException, UserException;

	Customer updateProfilePicture(Integer customerId, String file)
			throws UserException, IOException, CustomerException, UserException;

	Customer getCustomerByPhoneNumber(String phoneNumber) throws CustomerException, UserException;

	Customer getCustomerByEmail(String email) throws CustomerException, UserException;

	List<Customer> getCustomersByKeyword(String keyword) throws CustomerException, UserException;

	Customer getCustomersByAddressId(Integer addressId) throws CustomerException, AddressException, UserException;

	List<Customer> getCustomersByAddressVillageId(Integer villageId)
			throws CustomerException, VillageException, UserException;

	List<Customer> getCustomersByAddressVillageName(String villageName)
			throws CustomerException, VillageException, UserException;

	List<Customer> getCustomersByAddressPincode(Integer pincode)
			throws CustomerException, VillageException, UserException;

	List<Customer> getAllCustomersAgeGreaterThan(Integer age) throws CustomerException, UserException;

	List<Customer> getAllCustomersAgeLessThan(Integer age) throws CustomerException, UserException;

	Address getAddressOfCustomer(Integer customerId) throws AddressException, CustomerException, UserException;

	List<CustomerResponseModel> getCustomersByRank() throws CustomerException, UserException;

	List<CustomerResponseModel> getCustomersByCustomerNameContaining(String customerName)
			throws CustomerException, UserException;

	CustomerResponseModel getCustomerResponseModelByCustomerId(Integer customerId)
			throws UserException, CustomerException;

	Customer addFirstCustomer(FirstCustomerDTO firstCustomer) throws VillageException, ProductException, UserException;

	List<NameAndId> getCustomersName() throws CustomerException, UserException;

	CustomerPersonalDto getCustomerPersonalDetails(String customerCode) throws CustomerException;

}
