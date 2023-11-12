package com.madhu.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Address;
import com.madhu.exception.AddressException;
import com.madhu.service.AddressService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/addAddress")
	public ResponseEntity<GeneralResponse> addAddressHandler(@RequestBody Address address) {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Added ");
		generalResponse.setData(addressService.addAddress(address));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateAddress/{addressId}")
	public ResponseEntity<GeneralResponse> updateAddressHandler(@PathVariable Integer addressId,@RequestBody Address address) throws AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Updated ");
		generalResponse.setData(addressService.updateAddress(addressId,address));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<GeneralResponse> deleteAddressHandler(@PathVariable Integer addressId)
			throws AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Deleted with address Id "+addressId);
		generalResponse.setData(addressService.deleteAddress(addressId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAddress/{addressId}")
	public ResponseEntity<GeneralResponse> getAddressHandler(@PathVariable Integer addressId) throws AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found");
		generalResponse.setData(addressService.getAddress(addressId));

		return ResponseEntity.ok(generalResponse);
	}



	@GetMapping("/getAddressByVillageName/{villageName}")
	public ResponseEntity<GeneralResponse> getAddressesByVillageNameHandler(@PathVariable String villageName)
			throws AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found with Village Name "+villageName);
		generalResponse.setData(addressService.getAddressesByVillageName(villageName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAddressByVillageId/{villageId}")
	public ResponseEntity<GeneralResponse> getAddressesByVillageIdHandler(@PathVariable Integer villageId) throws AddressException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found with Village Id "+villageId);
		generalResponse.setData(addressService.getAddressesByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

}
