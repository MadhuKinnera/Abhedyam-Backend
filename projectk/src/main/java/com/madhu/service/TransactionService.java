package com.madhu.service;

import java.util.List;

import com.madhu.dto.RecordResponseModel;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;

public interface TransactionService {

	Transaction addTransaction(Transaction transaction) throws TransactionException;

	Transaction getTransactionById(Integer transactionId) throws TransactionException;

	Transaction updateTransaction(Integer transactionId,Transaction transaction) throws TransactionException;

	Transaction deleteTransactioById(Integer transactionId) throws TransactionException;

	List<Transaction> getAllTransactionsByCustomerId(Integer customerId) throws CustomerException, TransactionException;

	RecordResponseModel getRecordByTransactionId(Integer transactionId) throws TransactionException, RecordException;

	List<Transaction> getAllTransactionsByRank() throws TransactionException;

	
	
}
