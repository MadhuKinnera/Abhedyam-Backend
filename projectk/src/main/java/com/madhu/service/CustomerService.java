package com.madhu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.CustomerResponseModel;
import com.madhu.dto.FirstCustomerDTO;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.VillageException;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	Customer getCustomerById(Integer customerId) throws CustomerException;

	Customer updateCustomer(Integer customerId,Customer customer) throws CustomerException;

	Customer deleteCustomerById(Integer customerId) throws CustomerException;
	
	Customer updateProfilePicture(MultipartFile file) throws CustomerException;

	Customer getCustomerByPhoneNumber(String phoneNumber) throws CustomerException;

	Customer getCustomerByEmail(String email) throws CustomerException;

	List<Customer> getCustomersByKeyword(String keyword) throws CustomerException;

	Customer getCustomersByAddressId(Integer addressId) throws CustomerException, AddressException;

	List<Customer> getCustomersByAddressVillageId(Integer villageId)
			throws CustomerException, VillageException;

	List<Customer> getCustomersByAddressVillageName(String villageName)
			throws CustomerException, VillageException;

	List<Customer> getCustomersByAddressPincode(Integer pincode) throws CustomerException, VillageException;

	List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, RecordException;

	List<Customer> getAllCustomersAgeGreaterThan(Integer age) throws CustomerException;

	List<Customer> getAllCustomersAgeLessThan(Integer age) throws CustomerException;
	
	Address getAddressOfCustomer(Integer customerId)throws AddressException,CustomerException;

	
	List<CustomerResponseModel> getCustomersByRank()throws CustomerException;
	
	Customer addFirstCustomer(FirstCustomerDTO firstCustomer) throws VillageException, ProductException; 

	
}
