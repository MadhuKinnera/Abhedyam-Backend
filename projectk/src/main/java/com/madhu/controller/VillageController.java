package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;
import com.madhu.service.VillageService;

@RestController
@RequestMapping("/village")
public class VillageController {

	@Autowired
	private VillageService villageService;

	ResponseEntity<GeneralResponse> addVillage(Village village) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Added ");
		generalResponse.setData(villageService.addVillage(village));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getVillageByVillageId(Integer villageId) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By Village Id " + villageId);
		generalResponse.setData(villageService.getVillageByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> updateVillage(Integer villageId, Village village) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Updated with Village Id " + villageId);
		generalResponse.setData(villageService.updateVillage(villageId, village));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> deleteVillage(Integer villageId) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Deleted with Village Id " + villageId);
		generalResponse.setData(villageService.deleteVillage(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getRecordsByVillageId(Integer villageId) throws RecordException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Village Id " + villageId);
		generalResponse.setData(villageService.getRecordsByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getCustomersByVillageId(Integer villageId)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customers Found By Village Id " + villageId);
		generalResponse.setData(villageService.getCustomersByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getTransactionByVillageId(Integer villageId)
			throws TransactionException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transactions Found By Village Id " + villageId);
		generalResponse.setData(villageService.getTransactionByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getVillagesByRank() throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Villages By Rank");
		generalResponse.setData(villageService.getVillagesByRank());

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAddressByVillageId(Integer villageId) throws VillageException, AddressException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found By Village Id " + villageId);
		generalResponse.setData(villageService.getAddressByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getVillageByCustomerId(Integer customerId)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By CustomerId " + customerId);
		generalResponse.setData(villageService.getVillageByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getVillageByCustomerName(String customerName)
			throws CustomerException, VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By Customer Name " + customerName);
		generalResponse.setData(villageService.getVillageByCustomerName(customerName));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getVillagesByPincode(Integer pincode) throws VillageException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Villages Found By Pincode " + pincode);
		generalResponse.setData(villageService.getVillagesByPincode(pincode));

		return ResponseEntity.ok(generalResponse);
	}
}
