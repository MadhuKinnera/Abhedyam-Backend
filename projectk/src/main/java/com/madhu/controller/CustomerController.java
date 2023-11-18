package com.madhu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.AddressDTO;
import com.madhu.dto.CustomerDTO;
import com.madhu.dto.FirstCustomerDTO;
import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Customer;
import com.madhu.enums.Color;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.service.CustomerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	ResponseEntity<GeneralResponse> addCustomer(@RequestBody CustomerDTO customer)
			throws CustomerException, UserException, IOException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Added with Name " + customer.getCustomerName());
		generalResponse.setData(customerService.addCustomer(customer));

		return ResponseEntity.ok(generalResponse);

	}

	@PutMapping("/addKeywords/{customerId}")
	ResponseEntity<GeneralResponse> addKeywordsToCustomer(@PathVariable Integer customerId,
			@RequestBody List<String> keywords) throws CustomerException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Added ");
		generalResponse.setData(customerService.addKeywordsToCustomer(customerId, keywords));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateAddress/{customerId}")
	ResponseEntity<GeneralResponse> updateAddressToACustomer(@RequestBody AddressDTO dto,
			@PathVariable Integer customerId) throws CustomerException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Address Updated ");
		generalResponse.setData(customerService.updateAddressToACustomer(dto, customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/udpateFlag/{customerId}/{flag}")
	ResponseEntity<GeneralResponse> updateFlagOfACustomer(@PathVariable Integer customerId, @PathVariable Color flag)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Flag Updated to " + flag);
		generalResponse.setData(customerService.updateFlagOfACustomer(customerId, flag));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomer/{customerId}")
	ResponseEntity<GeneralResponse> getCustomer(@PathVariable Integer customerId)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found with Customer Id " + customerId);
		generalResponse.setData(customerService.getCustomerById(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateCustomer/{customerId}")
	ResponseEntity<GeneralResponse> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Updated Successfully with customer Id " + customerId);
		generalResponse.setData(customerService.updateCustomer(customerId, customer));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	ResponseEntity<GeneralResponse> deleteCustomer(@PathVariable Integer customerId)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();
		generalResponse.setMessage("Customer Deleted Successfully with customer Id " + customerId);
		generalResponse.setData(customerService.deleteCustomerById(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByPhone/{phoneNumber}")
	ResponseEntity<GeneralResponse> getCustomerByPhoneNumber(@PathVariable String phoneNumber)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer PhoneNumber " + phoneNumber);
		generalResponse.setData(customerService.getCustomerByPhoneNumber(phoneNumber));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByEmail/{email}")
	ResponseEntity<GeneralResponse> getCustomerByEmail(@PathVariable String email)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Email " + email);
		generalResponse.setData(customerService.getCustomerByEmail(email));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByKeyword/{keyword}")
	ResponseEntity<GeneralResponse> getCusstomersByKeyword(@PathVariable String keyword)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customers Found  with  Keyword " + keyword);
		generalResponse.setData(customerService.getCustomersByKeyword(keyword));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByAddress/{addressId}")
	ResponseEntity<GeneralResponse> getCustomerByAddressId(@PathVariable Integer addressId)
			throws CustomerException, AddressException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer AddressId " + addressId);
		generalResponse.setData(customerService.getCustomersByAddressId(addressId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByVillageId/{villageId}")
	ResponseEntity<GeneralResponse> getCustomerByAdressVillageId(@PathVariable Integer villageId)
			throws CustomerException, VillageException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Village Id " + villageId);
		generalResponse.setData(customerService.getCustomersByAddressVillageId(villageId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getCustomerByVillageName/{villageName}")
	ResponseEntity<GeneralResponse> getCustomerByAdressVillageName(@PathVariable String villageName)
			throws CustomerException, VillageException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Village Name " + villageName);
		generalResponse.setData(customerService.getCustomersByAddressVillageName(villageName));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getAddress/{customerId}")
	ResponseEntity<GeneralResponse> getAddressOfCustomer(@PathVariable Integer customerId)
			throws AddressException, CustomerException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Address Found ");
		generalResponse.setData(customerService.getAddressOfCustomer(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerByPincode/{pincode}")
	ResponseEntity<GeneralResponse> getCustomerByAdressPincode(@PathVariable Integer pincode)
			throws CustomerException, VillageException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Pincode " + pincode);
		generalResponse.setData(customerService.getCustomersByAddressPincode(pincode));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAllCustomersGreaterThanAge/{age}")
	ResponseEntity<GeneralResponse> getAllCustomerAgeGreaterThan(@PathVariable Integer age)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer age greater than " + age);
		generalResponse.setData(customerService.getAllCustomersAgeGreaterThan(age));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAllCustomersLessThanAge/{age}")
	ResponseEntity<GeneralResponse> getAllCustomerAgeLessThan(@PathVariable Integer age)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer age less than " + age);
		generalResponse.setData(customerService.getAllCustomersAgeLessThan(age));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerResponseModels")
	ResponseEntity<GeneralResponse> getCustomersByRankAndUserId() throws CustomerException, UserException {
		var generalResponse = new GeneralResponse();

		System.out.println("Inside get customers rank controller method");

		generalResponse.setMessage("Customer Response Models  Found ");
		generalResponse.setData(customerService.getCustomersByRank());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerResponseModelByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getCustomerResponseModelByCustomerId(@PathVariable Integer customerId)
			throws UserException, CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Response Model Found By Customer Id " + customerId);
		generalResponse.setData(customerService.getCustomerResponseModelByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomersByCustomerNameContains/{customerName}")
	ResponseEntity<GeneralResponse> getCustomersByCustomerNameContaining(@PathVariable String customerName)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Response Model Found By Customer Name " + customerName);
		generalResponse.setData(customerService.getCustomersByCustomerNameContaining(customerName));

		return ResponseEntity.ok(generalResponse);

	}

	@PostMapping("/addFirstTimeCustomer")
	ResponseEntity<GeneralResponse> addFistCustomerHandler(@RequestBody FirstCustomerDTO firstCustomer)
			throws VillageException, ProductException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("First Customer Added ");
		generalResponse.setData(customerService.addFirstCustomer(firstCustomer));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateProfilePicture/{customerId}")
	ResponseEntity<GeneralResponse> updateProfilePicture(@PathVariable Integer customerId, @RequestParam String image)
			throws UserException, IOException, CustomerException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Profile Picture Updated");
		generalResponse.setData(customerService.updateProfilePicture(customerId, image));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getPlainCustomers")
	ResponseEntity<GeneralResponse> getPlainCustomers() throws UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Plain Customers Found");
		generalResponse.setData(customerService.getPlainCustomers());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomersName")
	ResponseEntity<GeneralResponse> getCustomersName() throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customers Name Found");
		generalResponse.setData(customerService.getCustomersName());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerPersonalInformation/{customerCode}")
	ResponseEntity<GeneralResponse> getCustomerPersonalInformation(@PathVariable String customerCode)
			throws CustomerException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Personal Details");
		generalResponse.setData(customerService.getCustomerPersonalDetails(customerCode));

		return ResponseEntity.ok(generalResponse);
	}

}
