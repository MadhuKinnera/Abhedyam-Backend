package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.FirstCustomerDTO;
import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Customer;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.VillageException;
import com.madhu.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	ResponseEntity<GeneralResponse> addCustomer(@RequestBody Customer customer) {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Added ");
		generalResponse.setData(customerService.addCustomer(customer));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getCustomer/{customerId}")
	ResponseEntity<GeneralResponse> getCustomer(@PathVariable Integer customerId) throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found with Customer Id " + customerId);
		generalResponse.setData(customerService.getCustomerById(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateCustomer/{customerId}")
	ResponseEntity<GeneralResponse> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer)
			throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Updated Successfully with customer Id " + customerId);
		generalResponse.setData(customerService.updateCustomer(customerId, customer));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	ResponseEntity<GeneralResponse> deleteCustomer(@PathVariable Integer customerId) throws CustomerException {

		var generalResponse = new GeneralResponse();
		generalResponse.setMessage("Customer Deleted Successfully with customer Id " + customerId);
		generalResponse.setData(customerService.deleteCustomerById(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByPhone/{phoneNumber}")
	ResponseEntity<GeneralResponse> getCustomerByPhoneNumber(@PathVariable String phoneNumber)
			throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer PhoneNumber " + phoneNumber);
		generalResponse.setData(customerService.getCustomerByPhoneNumber(phoneNumber));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByEmail/{email}")
	ResponseEntity<GeneralResponse> getCustomerByEmail(@PathVariable String email) throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Email " + email);
		generalResponse.setData(customerService.getCustomerByEmail(email));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByKeyword/{keyword}")
	ResponseEntity<GeneralResponse> getCusstomersByKeyword(@PathVariable String keyword) throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customers Found  with  Keyword " + keyword);
		generalResponse.setData(customerService.getCustomersByKeyword(keyword));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByAddress/{addressId}")
	ResponseEntity<GeneralResponse> getCustomerByAddressId(@PathVariable Integer addressId)
			throws CustomerException, AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer AddressId " + addressId);
		generalResponse.setData(customerService.getCustomersByAddressId(addressId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByVillageId/{villageId}")
	ResponseEntity<GeneralResponse> getCustomerByAdressVillageId(@PathVariable Integer villageId)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Village Id " + villageId);
		generalResponse.setData(customerService.getCustomersByAddressVillageId(villageId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getCustomerByVillageName/{villageName}")
	ResponseEntity<GeneralResponse> getCustomerByAdressVillageName(@PathVariable String villageName)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Village Name " + villageName);
		generalResponse.setData(customerService.getCustomersByAddressVillageName(villageName));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getAddress/{customerId}")
	ResponseEntity<GeneralResponse> getAddressOfCustomer(@PathVariable Integer customerId)
			throws AddressException, CustomerException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Address Found ");
		generalResponse.setData(customerService.getAddressOfCustomer(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByPincode/{pincode}")
	ResponseEntity<GeneralResponse> getCustomerByAdressPincode(@PathVariable Integer pincode)
			throws CustomerException, VillageException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Pincode " + pincode);
		generalResponse.setData(customerService.getCustomersByAddressPincode(pincode));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getRecordByCustomerId(@PathVariable Integer customerId)
			throws CustomerException, RecordException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found  with customer Id " + customerId);
		generalResponse.setData(customerService.getRecordsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAllCustomersGreaterThanAge/{age}")
	ResponseEntity<GeneralResponse> getAllCustomerAgeGreaterThan(@PathVariable Integer age) throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer age greater than " + age);
		generalResponse.setData(customerService.getAllCustomersAgeGreaterThan(age));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAllCustomersLessThanAge/{age}")
	ResponseEntity<GeneralResponse> getAllCustomerAgeLessThan(@PathVariable Integer age) throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer age less than " + age);
		generalResponse.setData(customerService.getAllCustomersAgeLessThan(age));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAllCustomersByRank")
	ResponseEntity<GeneralResponse> getCustomerByRank() throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found By Rank ");
		generalResponse.setData(customerService.getCustomersByRank());

		return ResponseEntity.ok(generalResponse);
	}

	@PostMapping("/addFirstTimeCustomer")
	ResponseEntity<GeneralResponse> addFistCustomerHandler(@RequestBody FirstCustomerDTO firstCustomer)
			throws VillageException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("First Customer Added ");
		generalResponse.setData(customerService.addFirstCustomer(firstCustomer));

		return ResponseEntity.ok(generalResponse);
	}

}
