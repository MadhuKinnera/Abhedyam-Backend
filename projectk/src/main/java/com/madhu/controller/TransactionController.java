package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	ResponseEntity<GeneralResponse> addTransaction(Transaction transaction) throws TransactionException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Added ");
		generalResponse.setData(transactionService.addTransaction(transaction));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getTransactionById(Integer transactionId) throws TransactionException {

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

	ResponseEntity<GeneralResponse> deleteTransactioById(Integer transactionId) throws TransactionException{
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Deleted By Id "+transactionId);
		generalResponse.setData(transactionService.deleteTransactioById(transactionId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTransactionsByCustomerId(Integer customerId)
			throws CustomerException, TransactionException {
		var generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transactions Found By Customer Id "+customerId);
		generalResponse.setData(transactionService.getAllTransactionsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getRecordByTransactionId(Integer transactionId)
			throws TransactionException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Record Found By Transaction Id"+transactionId);
		generalResponse.setData(transactionService.getRecordByTransactionId(transactionId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTransactionsByRank() throws TransactionException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Transaction Found By Rank ");
		generalResponse.setData(transactionService.getAllTransactionsByRank());

		return ResponseEntity.ok(generalResponse);
	}

}
