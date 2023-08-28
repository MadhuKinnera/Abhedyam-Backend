package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

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

import jakarta.transaction.TransactionalException;

@Service
public class RecordServiceImpl implements RecordService {

	@Override
	public SaleRecord addRecord(SaleRecord saleRecord) throws RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleRecord updateRecord(Integer recordId, SaleRecord saleRecord) throws RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordResponseModel getRecordResponseModelByRecordId(Integer recordId) throws RecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecordResponseModel> getRecordResponseModelByCustomerId(Integer customerId)
			throws CustomerException, RecordException {
		// TODO Auto-generated method stub
		return null;
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
