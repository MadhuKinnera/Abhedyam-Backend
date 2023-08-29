package com.madhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.repository.TransactionRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Transaction addTransaction(Transaction transaction) throws TransactionException {

		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction getTransactionById(Integer transactionId) throws TransactionException {

		return transactionRepo.findById(transactionId).orElseThrow(
				() -> new TransactionException(" Transaction Not Found with Transaction Id " + transactionId));

	}

	@Override
	public Transaction updateTransaction(Integer transactionId, Transaction transaction) throws TransactionException {

		if (!utils.isTransactionExist(transactionId))
			throw new TransactionException("Transaction Not Found with Id " + transactionId);

		transaction.setTransactionId(transactionId);

		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction deleteTransactioById(Integer transactionId) throws TransactionException {
		Transaction transaction = getTransactionById(transactionId);

		transactionRepo.delete(transaction);

		return transaction;
	}

	@Override
	public List<Transaction> getAllTransactionsByCustomerId(Integer customerId)
			throws CustomerException, TransactionException {

		if (!utils.isCustomerExist(customerId))
			throw new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId);

		List<Transaction> transactions = transactionRepo.findBySaleRecordCustomerCustomerId(customerId);

		if (transactions.isEmpty())
			throw new TransactionException("No Transactions Found with The Customer Id " + customerId);

		return transactions;
	}

	@Override
	public SaleRecord getRecordByTransactionId(Integer transactionId) throws TransactionException, RecordException {

		Transaction transaction = getTransactionById(transactionId);

		return transaction.getSaleRecord();

	}

	@Override
	public List<Transaction> getAllTransactionsByRank() throws TransactionException {

		List<Transaction> transactions =  transactionRepo.findAll();
		
		if(transactions.isEmpty())
			throw new TransactionException("No Transctions Found ");

			
			
		return transactions;
	}

}
