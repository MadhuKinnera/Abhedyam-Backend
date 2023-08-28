package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.RecordResponseModel;
import com.madhu.entity.Address;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Remainder;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.VillageException;
import com.madhu.repository.RecordRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

import jakarta.transaction.TransactionalException;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public SaleRecord addRecord(SaleRecord saleRecord) throws RecordException {

		return recordRepo.save(saleRecord);
	}

	@Override
	public SaleRecord updateRecord(Integer recordId, SaleRecord saleRecord) throws RecordException {

		if (!utils.isRecordExist(recordId))
			throw new RecordException(Constants.RECORD_ID_NOT_FOUND + recordId);

		return recordRepo.save(saleRecord);
	}

	@Override
	public SaleRecord getRecordByRecordId(Integer recordId) throws RecordException {

		return recordRepo.findById(recordId)
				.orElseThrow(() -> new RecordException(Constants.RECORD_ID_NOT_FOUND + recordId));
	}

	@Override
	public List<SaleRecord> getRecordByCustomerId(Integer customerId) throws CustomerException, RecordException {

		List<SaleRecord> records = recordRepo.findByCustomerCustomerId(customerId);

		if (records.isEmpty())
			throw new RecordException(Constants.NO_RECORDS_FOUND_WITH_CUSTOMER_ID + customerId);

		return records;

	}

	@Override
	public List<RecordResponseModel> getRecordRecordResponseByRank() throws RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleRecord deleteRecordByRecordId(Integer recordId) throws RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsBetweenTimeStampsAndCustomerId(LocalDate fromDate, LocalDate toDate,
			Integer customerId) throws CustomerException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByVillageId(Integer villageId) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByVillageName(String villageName) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByMandal(String mandal) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByPincode(Integer pincode) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByDistrict(String district) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByRecordId(Integer recordId)
			throws RecordException, TransactionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getRecordsByProductId(Integer productId) throws RecordException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getRecordsByProductName(String productName) throws RecordException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remainder> getRemaindersByRecordId(String recordId) throws RemainderException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByEmail(String email) throws CustomerException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByCustomerName(String name) throws CustomerException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village getVillageByRecordId(Integer recordId) throws VillageException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddressByRecordId(Integer recordId) throws AddressException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByRecordId(Integer recordId) throws ProductException, RecordException {
		// TODO Auto-generated method stub
		return null;
	}

}
