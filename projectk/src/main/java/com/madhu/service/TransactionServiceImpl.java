package com.madhu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.madhu.dto.RecordResponseModel;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Override
	public Transaction addTransaction(Transaction transaction) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransactionById(Integer transactionId) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction updateTransaction(Integer transactionId, Transaction transaction) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction deleteTransactioById(Integer transactionId) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactionsByCustomerId(Integer customerId)
			throws CustomerException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordResponseModel getRecordByTransactionId(Integer transactionId)
			throws TransactionException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactionsByRank() throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
