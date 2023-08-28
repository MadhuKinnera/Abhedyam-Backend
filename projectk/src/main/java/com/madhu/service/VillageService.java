package com.madhu.service;

import java.util.List;

import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;

public interface VillageService {

	Village addVillage(Village village) throws VillageException;

	Village getVillageByVillageId(Integer villageId) throws VillageException;

	Village updateVillage(Integer villageId,Village village) throws VillageException;

	Village deleteVillage(Integer villageId) throws VillageException;

	List<SaleRecord> getRecordsByVillageId(Integer villageId) throws RecordException, VillageException;

	List<Customer> getCustomersByVillageId(Integer villageId) throws CustomerException, VillageException;
	
	List<Transaction> getTransactionByVillageId(Integer villageId)  throws TransactionException,VillageException;
	
	List<Village> getVillagesByRank()throws VillageException;
	
	List<Address> getAddressByVillageId(Integer villageId)throws VillageException,AddressException;
	
	Village getVillageByCustomerId(Integer customerId)throws CustomerException,VillageException;
	
	Village getVillageByCustomerName(String customerName)throws CustomerException,VillageException;
	
	List<Village> getVillagesByPincode(Integer pincode) throws VillageException;
	
	
	
	

}
