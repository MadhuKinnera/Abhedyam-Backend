package com.madhu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.TransactionDTO;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;

public interface TransactionService {

	Transaction addTransaction(TransactionDTO dto) throws TransactionException,RecordException;

	Transaction getTransactionById(Integer transactionId) throws TransactionException;

	Transaction updateTransaction(Integer transactionId,Transaction transaction) throws TransactionException;

	Transaction deleteTransactioById(Integer transactionId) throws TransactionException;
	
	Transaction uploadTransactionProofImage(MultipartFile transactionImage)throws TransactionException;

	List<Transaction> getAllTransactionsByCustomerId(Integer customerId) throws CustomerException, TransactionException;

	SaleRecord getRecordByTransactionId(Integer transactionId) throws TransactionException, RecordException;

	List<Transaction> getAllTransactionsByRank() throws TransactionException;

	
	
}
