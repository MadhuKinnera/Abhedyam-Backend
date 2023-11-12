package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.dto.TransactionDTO;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.service.TransactionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	
	@PostMapping("/addTransaction")
	ResponseEntity<GeneralResponse> addTransaction(@RequestBody TransactionDTO transaction) throws TransactionException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Added ");
		generalResponse.setData(transactionService.addTransaction(transaction));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getTransaction/{transactionId}")
	ResponseEntity<GeneralResponse> getTransactionById(@PathVariable Integer transactionId) throws TransactionException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Found By Id "+transactionId);
		generalResponse.setData(transactionService.getTransactionById(transactionId));

		return ResponseEntity.ok(generalResponse);
	}
	

	ResponseEntity<GeneralResponse> updateTransaction(Integer transactionId,Transaction transaction) throws TransactionException{
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Updated By Id "+transactionId);
		generalResponse.setData(transactionService.updateTransaction(transactionId, transaction));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteTransaction/{transactionId}")
	ResponseEntity<GeneralResponse> deleteTransactioById(@PathVariable Integer transactionId) throws TransactionException{
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Deleted By Id "+transactionId);
		generalResponse.setData(transactionService.deleteTransactioById(transactionId));

		return ResponseEntity.ok(generalResponse);
	}

	
	@GetMapping("/getTransactionsByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getTransactionsByCustomerId(@PathVariable Integer customerId)
			throws CustomerException, TransactionException {
		var generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transactions Found By Customer Id "+customerId);
		generalResponse.setData(transactionService.getAllTransactionsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRecordByTransactionId/{transactionId}")
	ResponseEntity<GeneralResponse> getRecordByTransactionId(@PathVariable Integer transactionId)
			throws TransactionException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Found By Transaction Id"+transactionId);
		generalResponse.setData(transactionService.getRecordByTransactionId(transactionId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getTransactions")
	ResponseEntity<GeneralResponse> getAllTransactionsByRank() throws TransactionException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Found By Rank ");
		generalResponse.setData(transactionService.getAllTransactionsByRank());

		return ResponseEntity.ok(generalResponse);
	}

}
