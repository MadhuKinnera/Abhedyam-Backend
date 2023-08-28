package com.madhu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madhu.repository.AddressRepo;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.RecordRepo;
import com.madhu.repository.TransactionRepo;
import com.madhu.repository.VillageRepo;

@Component
public class CommonUtils {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private VillageRepo villageRepo;

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private TransactionRepo transactionRepo;

	public boolean isCustomerExist(Integer customerId) {
		return customerRepo.findById(customerId).isPresent();
	}

	public boolean isVillageExist(Integer villageId) {
		return villageRepo.findById(villageId).isPresent();
	}

	public boolean isVillageExistByName(String villageName) {
		return villageRepo.findByVillageName(villageName).isPresent();
	}

	public boolean isPincodeExist(Integer pincode) {
		return villageRepo.findByPincode(pincode).isPresent();
	}

	public boolean isRecordExist(Integer recordId) {
		return recordRepo.findById(recordId).isPresent();
	}

	public boolean isTransactionExist(Integer transactionId) {
		return transactionRepo.findById(transactionId).isPresent();
	}

	public boolean isAddressExist(Integer addressId) {
		return addressRepo.findById(addressId).isPresent();
	}
	
	public boolean isProductExist(Integer productId) {
		return productRepo.findById(productId).isPresent();
	}

}
