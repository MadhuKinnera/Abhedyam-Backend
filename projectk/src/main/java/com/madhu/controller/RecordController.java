package com.madhu.controller;

import java.time.LocalDate;

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
import com.madhu.dto.RecordDTO;
import com.madhu.entity.SaleRecord;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;
import com.madhu.service.RecordService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.TransactionalException;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private RecordService recordService;

	@PostMapping("/addRecord")
	ResponseEntity<GeneralResponse> addRecordHandler(@RequestBody RecordDTO saleRecord)
			throws RecordException, CustomerException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Added ");
		generalResponse.setData(recordService.addRecord(saleRecord));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateRecord/{recordId}")
	ResponseEntity<GeneralResponse> updateRecordHandler(@PathVariable Integer recordId,
			@RequestBody SaleRecord saleRecord) throws RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Updated with Id " + recordId);
		generalResponse.setData(recordService.updateRecord(recordId, saleRecord));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecord/{recordId}")
	ResponseEntity<GeneralResponse> getRecordByRecordIdHandler(@PathVariable Integer recordId) throws RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Found with Id ");
		generalResponse.setData(recordService.getRecordByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecords/{customerId}")
	ResponseEntity<GeneralResponse> getRecordsByCustomerIdHandler(@PathVariable Integer customerId)
			throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Found With Customer Id " + customerId);
		generalResponse.setData(recordService.getRecordsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecords")
	ResponseEntity<GeneralResponse> getRecordsByRankHandler() throws RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Ranks ");
		generalResponse.setData(recordService.getRecordRecordResponseByRank());

		return ResponseEntity.ok(generalResponse);
	}

	// ======================================

	@DeleteMapping("/deleteRecord/{recordId}")
	ResponseEntity<GeneralResponse> deleteRecordByRecordId(@PathVariable Integer recordId) throws RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Deleted with Id " + recordId);
		generalResponse.setData(recordService.deleteRecordByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordBetweenStartDates/{fromDate}/{toDate}")
	ResponseEntity<GeneralResponse> getRecordsBetweenStartDatesTimeStamps(@PathVariable LocalDate fromDate,
			@PathVariable LocalDate toDate) throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found between " + fromDate + " and " + toDate);
		generalResponse.setData(recordService.getRecordsBetweenStartDateTimeStamps(fromDate, toDate));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordBetweenEndDates/{fromDate}/{toDate}")
	ResponseEntity<GeneralResponse> getRecordsBetweenEndDatesTimeStamps(@PathVariable LocalDate fromDate,
			@PathVariable LocalDate toDate) throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found between " + fromDate + " and " + toDate);
		generalResponse.setData(recordService.getRecordsBetweenEndDateTimeStamps(fromDate, toDate));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByVillageId/{villageId}")
	ResponseEntity<GeneralResponse> getRecordsByVillageId(@PathVariable Integer villageId)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Village Id " + villageId);
		generalResponse.setData(recordService.getRecordsByVillageId(villageId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByVillageName/{villageName}")
	ResponseEntity<GeneralResponse> getRecordsByVillageName(@PathVariable String villageName)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Village Name " + villageName);
		generalResponse.setData(recordService.getRecordsByVillageName(villageName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByMandal/{mandal}")
	ResponseEntity<GeneralResponse> getRecordsByMandal(@PathVariable String mandal)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Mandal " + mandal);
		generalResponse.setData(recordService.getRecordsByMandal(mandal));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByPincode/{pincode}")
	ResponseEntity<GeneralResponse> getRecordsByPincode(@PathVariable Integer pincode)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Pincode " + pincode);
		generalResponse.setData(recordService.getRecordsByPincode(pincode));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByDistrict/{district}")
	ResponseEntity<GeneralResponse> getRecordsByDistrict(@PathVariable String district)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By District " + district);
		generalResponse.setData(recordService.getRecordsByDistrict(district));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getRecordsByCustomerId(@PathVariable Integer customerId)
			throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Customer Id  " + customerId);
		generalResponse.setData(recordService.getRecordsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getTransactionsByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getTransactionsByRecordId(@PathVariable Integer recordId)
			throws RecordException, TransactionalException, TransactionException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transactions Found By Record Id " + recordId);
		generalResponse.setData(recordService.getTransactionsByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByProductId/{productId}")
	ResponseEntity<GeneralResponse> getRecordsByProductId(@PathVariable Integer productId)
			throws RecordException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Product Id " + productId);
		generalResponse.setData(recordService.getRecordsByProductId(productId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByProductName/{productName}")
	ResponseEntity<GeneralResponse> getRecordsByProductName(@PathVariable String productName)
			throws RecordException, ProductException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Product Name  " + productName);
		generalResponse.setData(recordService.getRecordsByProductName(productName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRemaindersByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getRemaindersByRecordId(@PathVariable Integer recordId)
			throws RemainderException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainders Found By Record Id " + recordId);
		generalResponse.setData(recordService.getRemaindersByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByEmail/{email}")
	ResponseEntity<GeneralResponse> getRecordsByEmail(@PathVariable String email)
			throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Records Found By Email  " + email);
		generalResponse.setData(recordService.getRecordsByEmail(email));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByCustomerName/{customerName}")
	ResponseEntity<GeneralResponse> getRecordsByCustomerName(@PathVariable String customerName)
			throws CustomerException, RecordException {
		var generalResponse = new GeneralResponse();

		System.out.println(customerName);

		generalResponse.setMessage("Records Found By Customer Name " + customerName);
		generalResponse.setData(recordService.getRecordsByCustomerName(customerName));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getVillageByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getVillageByRecordId(@PathVariable Integer recordId)
			throws VillageException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Village Found By Record Id " + recordId);
		generalResponse.setData(recordService.getVillageByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getAddressByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getAddressByRecordId(@PathVariable Integer recordId)
			throws AddressException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Address Found By Record Id " + recordId);
		generalResponse.setData(recordService.getAddressByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getProductByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getProductByRecordId(@PathVariable Integer recordId)
			throws ProductException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Product Found By Record Id " + recordId);
		generalResponse.setData(recordService.getProductByRecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordResponseModels")
	ResponseEntity<GeneralResponse> getRecordResponseModels() throws RecordException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Response Models Found");
		generalResponse.setData(recordService.getRecordResponseModels());

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordResponeModelByRecordId/{recordId}")
	ResponseEntity<GeneralResponse> getRecordResponseModelByrecordId(@PathVariable Integer recordId)
			throws RecordException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Response Model Found By Record Id " + recordId);
		generalResponse.setData(recordService.getRecordResponseModelByrecordId(recordId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordsByRecordIdOrCustomerNameOrProductName/{recordId}/{customerName}/{productName}")
	ResponseEntity<GeneralResponse> getRecordsContainingRecordIdOrCustomerNameOrProductName(
			@PathVariable Integer recordId, @PathVariable String customerName, @PathVariable String productName)
			throws RecordException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Response Models Found In Search " + recordId +" " + customerName+" " + productName);
		generalResponse.setData(recordService.getRecordsContainingRecordIdOrCustomerNameOrProductName(recordId,
				customerName, productName));

		return ResponseEntity.ok(generalResponse);
	}

}
