package com.madhu.controller;

import com.madhu.dto.AddressDTO;
import com.madhu.dto.CustomerDTO;
import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Customer;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	ResponseEntity<GeneralResponse> addCustomer(@RequestBody CustomerDTO customer)
			throws CustomerException, UserException, IOException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Added with Name " + customer.getCustomerName());
		generalResponse.setData(customerService.addCustomer(customer));

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


	@GetMapping("/{customerId}")
	ResponseEntity<GeneralResponse> getCustomer(@PathVariable Integer customerId)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found with Customer Id " + customerId);
		generalResponse.setData(customerService.getCustomerById(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/{customerId}")
	ResponseEntity<GeneralResponse> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer)
			throws CustomerException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Updated Successfully with customer Id " + customerId);
		generalResponse.setData(customerService.updateCustomer(customerId, customer));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/{customerId}")
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

	@GetMapping("/getCustomerByPincode/{pincode}")
	ResponseEntity<GeneralResponse> getCustomerByAdressPincode(@PathVariable Integer pincode)
			throws CustomerException, VillageException, UserException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Found  with customer Pincode " + pincode);
		generalResponse.setData(customerService.getCustomersByAddressPincode(pincode));

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
