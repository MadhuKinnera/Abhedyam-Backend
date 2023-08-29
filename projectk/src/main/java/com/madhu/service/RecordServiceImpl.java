package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;
import com.madhu.repository.RecordRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

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
	public List<SaleRecord> getRecordRecordResponseByRank() throws RecordException {

		List<SaleRecord> records = recordRepo.findAll();

		if (records.isEmpty())
			throw new RecordException("No Records Found ");

		return records;
	}

	@Override
	public SaleRecord deleteRecordByRecordId(Integer recordId) throws RecordException {
		SaleRecord record = getRecordByRecordId(recordId);
		recordRepo.delete(record);
		return record;
	}

	@Override
	public List<SaleRecord> getRecordsByVillageId(Integer villageId) throws VillageException, RecordException {
		List<SaleRecord> records = recordRepo.findByAddressVillageVillageId(villageId);

		if (records.isEmpty())
			throw new VillageException("No Records Found with Village Id " + villageId);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsByVillageName(String villageName) throws VillageException, RecordException {
		List<SaleRecord> records = recordRepo.findByAddressVillageVillageName(villageName);

		if (records.isEmpty())
			throw new VillageException("No Records Found with Village Name " + villageName);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsByMandal(String mandal) throws VillageException, RecordException {
		List<SaleRecord> records = recordRepo.findByAddressVillageMandal(mandal);

		if (records.isEmpty())
			throw new VillageException("No Records Found with Mandal  " + mandal);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsByPincode(Integer pincode) throws VillageException, RecordException {
		List<SaleRecord> records = recordRepo.findByAddressVillagePincode(pincode);

		if (records.isEmpty())
			throw new VillageException("No Records Found with Pincode " + pincode);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsByDistrict(String district) throws VillageException, RecordException {
		List<SaleRecord> records = recordRepo.findByAddressVillageDistrict(district);

		if (records.isEmpty())
			throw new VillageException("No Records Found with District " + district);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, RecordException {

		List<SaleRecord> records = recordRepo.findByCustomerCustomerId(customerId);

		if (records.isEmpty())
			throw new CustomerException("No Records Found with Customer Id " + customerId);

		return records;

	}

	@Override
	public List<Transaction> getTransactionsByRecordId(Integer recordId) throws RecordException, TransactionException {

		SaleRecord record = getRecordByRecordId(recordId);

		if (record.getTransactions().isEmpty())
			throw new TransactionException("No Transactions Found with Record Id " + recordId);

		return record.getTransactions();

	}

	@Override
	public List<SaleRecord> getRecordsByProductId(Integer productId) throws RecordException, ProductException {
		List<SaleRecord> records = recordRepo.findByProductProductId(productId);

		if (records.isEmpty())
			throw new RecordException("No Records Found with Product Id " + productId);

		return records;

	}

	@Override
	public List<SaleRecord> getRecordsByProductName(String productName) throws RecordException, ProductException {
		List<SaleRecord> records = recordRepo.findByProductProductName(productName);

		if (records.isEmpty())
			throw new RecordException("No Records Found with Product Name " + productName);

		return records;
	}

	@Override
	public List<Remainder> getRemaindersByRecordId(Integer recordId) throws RemainderException, RecordException {
		SaleRecord record = getRecordByRecordId(recordId);

		if (record.getRemainders().isEmpty())
			throw new RemainderException("No Transactions Found with Record Id " + recordId);

		return record.getRemainders();
	}

	@Override
	public List<SaleRecord> getRecordsByEmail(String email) throws CustomerException, RecordException {

		List<SaleRecord> records = recordRepo.findByCustomerEmail(email);

		if (records.isEmpty())
			throw new CustomerException("No Records Found with Customer Email " + email);

		return records;

	}

	@Override
	public List<SaleRecord> getRecordsByCustomerName(String name) throws CustomerException, RecordException {
		List<SaleRecord> records = recordRepo.findByCustomerCustomerName(name);

		if (records.isEmpty())
			throw new CustomerException("No Records Found with Customer Name " + name);

		return records;
	}

	@Override
	public Village getVillageByRecordId(Integer recordId) throws VillageException, RecordException {

		SaleRecord record = getRecordByRecordId(recordId);

		return record.getAddress().getVillage();
	}

	@Override
	public Address getAddressByRecordId(Integer recordId) throws AddressException, RecordException {
		SaleRecord record = getRecordByRecordId(recordId);

		return record.getAddress();
	}

	@Override
	public Product getProductByRecordId(Integer recordId) throws ProductException, RecordException {
		SaleRecord record = getRecordByRecordId(recordId);

		return record.getProduct();
	}

	@Override
	public List<SaleRecord> getRecordsBetweenStartDateTimeStampsAndCustomerId(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, RecordException {
		List<SaleRecord> records = recordRepo.findByStartDateBetween(fromDate, toDate);

		if (records.isEmpty())
			throw new CustomerException("No Records Found from" + fromDate + " and " + toDate);

		return records;
	}

	@Override
	public List<SaleRecord> getRecordsBetweenEndDateTimeStampsAndCustomerId(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, RecordException {
		List<SaleRecord> records = recordRepo.findByEndDateBetween(fromDate, toDate);

		if (records.isEmpty())
			throw new CustomerException("No Records Found from" + fromDate + " and " + toDate);

		return records;
	}

	@Override
	public Customer getCustomerByRecordId(Integer recordId) throws RecordException {
		SaleRecord record = getRecordByRecordId(recordId);

		return record.getCustomer();
	}

}
