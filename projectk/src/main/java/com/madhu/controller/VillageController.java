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
import com.madhu.dto.VillageDTO;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.service.VillageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/village")
public class VillageController {

	@Autowired
	private VillageService villageService;

	@PostMapping("/addVillage")
	ResponseEntity<GeneralResponse> addVillage(@RequestBody VillageDTO village) throws VillageException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Added ");
		generalResponse.setData(villageService.addVillage(village));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillage/{villageId}")
	ResponseEntity<GeneralResponse> getVillageByVillageId(@PathVariable Integer villageId) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By Village Id " + villageId);
		generalResponse.setData(villageService.getVillageByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateVillage/{villageId}")
	ResponseEntity<GeneralResponse> updateVillage(@PathVariable Integer villageId, @RequestBody Village village)
			throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Updated with Village Id " + villageId);
		generalResponse.setData(villageService.updateVillage(villageId, village));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteVillage/{villageId}")
	ResponseEntity<GeneralResponse> deleteVillage(@PathVariable Integer villageId) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Deleted with Village Id " + villageId);
		generalResponse.setData(villageService.deleteVillage(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillages")
	ResponseEntity<GeneralResponse> getVillagesByRank() throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Villages By Rank");
		generalResponse.setData(villageService.getVillagesByRank());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAddressesByVillageId/{villageId}")
	ResponseEntity<GeneralResponse> getAddressByVillageId(@PathVariable Integer villageId)
			throws VillageException, AddressException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found By Village Id " + villageId);
		generalResponse.setData(villageService.getAddressByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getVillageByCustomerId(@PathVariable Integer customerId)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By CustomerId " + customerId);
		generalResponse.setData(villageService.getVillageByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageByCustomerName/{customerName}")
	ResponseEntity<GeneralResponse> getVillageByCustomerName(@PathVariable String customerName)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By Customer Name " + customerName);
		generalResponse.setData(villageService.getVillageByCustomerName(customerName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillagesByPincode/{pincode}")
	ResponseEntity<GeneralResponse> getVillagesByPincode(@PathVariable Integer pincode) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Villages Found By Pincode " + pincode);
		generalResponse.setData(villageService.getVillagesByPincode(pincode));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageWiseData")
	ResponseEntity<GeneralResponse> getVillageWiseData() throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Found Village Wise Data ");
		generalResponse.setData(villageService.getVillageWiseData());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageWiseDataByVillageId/{villageId}")
	ResponseEntity<GeneralResponse> getVillageWiseDataByVillageId(@PathVariable Integer villageId)
			throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Wise Data Found With Village Id " + villageId);
		generalResponse.setData(villageService.getVillageWiseDataByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageWiseDataByVillageName/{villageName}")
	ResponseEntity<GeneralResponse> getVillageWiseDataByVillageName(@PathVariable String villageName)
			throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Wise Data Found With Village Name " + villageName);
		generalResponse.setData(villageService.getVillageWiseDataByVillageNameContaining(villageName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillagesName")
	ResponseEntity<GeneralResponse> getVillageNames() throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Names Found ");
		generalResponse.setData(villageService.getVillageNames());

		return ResponseEntity.ok(generalResponse);
	}

}
