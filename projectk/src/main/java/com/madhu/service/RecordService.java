package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

import com.madhu.dto.RecordDTO;
import com.madhu.dto.RecordResponseModel;
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

public interface RecordService {

	SaleRecord addRecord(RecordDTO dto) throws RecordException,CustomerException,ProductException;

	SaleRecord updateRecord(Integer recordId, SaleRecord saleRecord) throws RecordException;

	SaleRecord getRecordByRecordId(Integer recordId) throws RecordException;

	List<SaleRecord> getRecordByCustomerId(Integer customerId) throws CustomerException, RecordException;

	List<SaleRecord> getRecordRecordResponseByRank() throws RecordException;

	// ==============================

	SaleRecord deleteRecordByRecordId(Integer recordId) throws RecordException;

	List<SaleRecord> getRecordsBetweenStartDateTimeStamps(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, RecordException;
	
	List<SaleRecord> getRecordsBetweenEndDateTimeStamps(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, RecordException;

	List<SaleRecord> getRecordsByVillageId(Integer villageId) throws VillageException, RecordException;

	List<SaleRecord> getRecordsByVillageName(String villageName) throws VillageException, RecordException;

	List<SaleRecord> getRecordsByMandal(String mandal) throws VillageException, RecordException;

	List<SaleRecord> getRecordsByPincode(Integer pincode) throws VillageException, RecordException;

	List<SaleRecord> getRecordsByDistrict(String district) throws VillageException, RecordException;

	List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, RecordException;

	List<Transaction> getTransactionsByRecordId(Integer recordId) throws RecordException, TransactionException;

	List<SaleRecord> getRecordsByProductId(Integer productId) throws RecordException, ProductException;

	List<SaleRecord> getRecordsByProductName(String productName) throws RecordException, ProductException;

	List<Remainder> getRemaindersByRecordId(Integer recordId) throws RemainderException, RecordException;

	List<SaleRecord> getRecordsByEmail(String email) throws CustomerException, RecordException;

	List<SaleRecord> getRecordsByCustomerName(String name) throws CustomerException, RecordException;

	Village getVillageByRecordId(Integer recordId) throws VillageException, RecordException;

	Address getAddressByRecordId(Integer recordId) throws AddressException, RecordException;

	Product getProductByRecordId(Integer recordId) throws ProductException, RecordException;
	
	Customer getCustomerByRecordId(Integer recordId) throws  RecordException;

	//record response models
	List<RecordResponseModel> getRecordResponseModels() throws RecordException;
	
	RecordResponseModel getRecordResponseModelByrecordId(Integer recordId) throws RecordException;
	
	List<RecordResponseModel> getRecordsContainingRecordIdOrCustomerNameOrProductName(Integer recordId,String customerName,String productName) throws RecordException;
	
	
	
}
