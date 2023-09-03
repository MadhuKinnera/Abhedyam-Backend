package com.madhu.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.User;
import com.madhu.entity.Village;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.repository.AddressRepo;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.RecordRepo;
import com.madhu.repository.RemainderRepo;
import com.madhu.repository.TransactionRepo;
import com.madhu.repository.UserRepo;
import com.madhu.repository.VillageRepo;

import jakarta.annotation.PostConstruct;

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
	private RemainderRepo remainderRepo;

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private Cloudinary cloudinary;

	private Integer userId;

	@PostConstruct
	private void assignUserId() throws UserException {
		this.userId = getUserIdFromContext();
	}

	public boolean isCustomerExist(Integer customerId) {
		return customerRepo.findById(customerId).isPresent();
	}

	public boolean isVillageExist(Integer villageId) {
		return villageRepo.findById(villageId).isPresent();
	}

	public boolean isVillageExistByName(String villageName) {
		return villageRepo.findByVillageNameAndUserUserId(villageName, userId).isPresent();
	}

	public boolean isPincodeExist(Integer pincode) {
		return !villageRepo.findByPincodeAndUserUserId(pincode, userId).isEmpty();
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

	public boolean isRemainderExist(Integer remainderId) {
		return remainderRepo.findById(remainderId).isPresent();
	}

	public boolean isAuthorizedForCustomer(Integer customerId) throws CustomerException, UserException {

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId));

		User user = getUserFromContext();

		if (!customer.getUser().getUserId().equals(user.getUserId()))
			throw new UserException(Constants.UN_AUTHORIZED_USER_FOR_CUSTOMER + customerId);

		return true;
	}

	public boolean isAuthorizedForProduct(Integer productId) throws UserException, ProductException {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductException(Constants.PRODUCT_ID_NOT_FOUND + productId));

		User user = getUserFromContext();

		if (!product.getUser().getUserId().equals(user.getUserId()))
			throw new UserException(Constants.UN_AUTHORIZED_USER_FOR_PRODUCT + productId);

		return true;

	}

	public boolean isAuthorizedForVillage(Integer villageId) throws UserException, VillageException {

		Village village = villageRepo.findById(villageId)
				.orElseThrow(() -> new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId));

		User user = getUserFromContext();

		if (!village.getUser().getUserId().equals(user.getUserId()))
			throw new VillageException(Constants.UN_AUTHORIZED_USER_FOR_VILLAGE + villageId);

		return true;
	}

	public User getUserFromContext() throws UserException {

		// get user from context

		String email = "admin2@gmail.com";

		User user = userRepo.findByEmail(email).orElseThrow(() -> new UserException("User Not Logged In "));

		return user;
	}

	public Integer getUserIdFromContext() throws UserException {

		User user = getUserFromContext();

		return user.getUserId();
	}

	@SuppressWarnings("rawtypes")
	public String convertImageToUrl(MultipartFile file) throws IOException {
		Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

		return data.get("secure_url").toString();
	}
	
	

}
