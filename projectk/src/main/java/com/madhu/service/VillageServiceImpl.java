package com.madhu.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

@Service
public class VillageServiceImpl implements VillageService{

	@Override
	public Village addVillage(Village village) throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village getVillageByVillageId(Integer villageId) throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village updateVillage(Integer villageId, Village village) throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village deleteVillage(Integer villageId) throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleRecord> getRecordsByVillageId(Integer villageId) throws RecordException, VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByVillageId(Integer villageId) throws CustomerException, VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByVillageId(Integer villageId)
			throws TransactionException, VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Village> getVillagesByRank() throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAddressByVillageId(Integer villageId) throws VillageException, AddressException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village getVillageByCustomerId(Integer customerId) throws CustomerException, VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Village getVillageByCustomerName(String customerName) throws CustomerException, VillageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Village> getVillagesByPincode(Integer pincode) throws VillageException {
		// TODO Auto-generated method stub
		return null;
	}

}
