package com.madhu.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.TransactionDTO;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.repository.RecordRepo;
import com.madhu.repository.TransactionRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Transaction addTransaction(TransactionDTO dto) throws TransactionException, RecordException {

		var transaction = new Transaction();

		SaleRecord record = recordRepo.findById(dto.getRecordId())
				.orElseThrow(() -> new RecordException(Constants.RECORD_ID_NOT_FOUND + dto.getRecordId()));

		
		if(dto.getAmount()>record.getDueAmount()) 
			throw new TransactionException("Your Amount "+dto.getAmount()+" is Greater than Due Amount "+record.getDueAmount());
		
		
		transaction.setAmount(dto.getAmount());
		transaction.setDescription(dto.getDescription());
		transaction.setSaleRecord(record);
		transaction.setTimestamp(LocalDateTime.now());
		transaction.setModeOfPayment(dto.getModeOfPayment());

		record.setDueAmount(record.getDueAmount() - dto.getAmount());

		recordRepo.save(record);

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

		List<Transaction> transactions = transactionRepo.findAll();

		if (transactions.isEmpty())
			throw new TransactionException("No Transctions Found ");

		return transactions;
	}

	@Override
	public Transaction uploadTransactionProofImage(MultipartFile transactionImage) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
