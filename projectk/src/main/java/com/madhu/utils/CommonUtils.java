package com.madhu.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.madhu.entity.User;
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
	PasswordEncoder encoder;

	@Autowired
	private Cloudinary cloudinary;

	//public Integer userId;
	
	
	

	@Autowired
	private UserInfo userInfo;

	private static final String ALGORITHM = "AES";

	private static final String key = "Madhu123Madhu123";

	public boolean isCustomerExist(Integer customerId) {
		return customerRepo.findById(customerId).isPresent();
	}

	public boolean isVillageExist(Integer villageId) {
		return villageRepo.findById(villageId).isPresent();
	}

	public boolean isVillageExistByName(String villageName) {
		return villageRepo.findByVillageNameAndUserUserId(villageName, userInfo.getUserId()).isPresent();
	}

	public boolean isPincodeExist(Integer pincode) {
		return !villageRepo.findByPincodeAndUserUserId(pincode, userInfo.getUserId()).isEmpty();
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
		return productRepo.findByProductIdAndUserUserId(productId, userInfo.getUserId()).isPresent();
	}

	public boolean isRemainderExist(Integer remainderId) {
		return remainderRepo.findById(remainderId).isPresent();
	}

	public boolean isUserExist(String email) {
		return userRepo.findByEmail(email).isPresent();
	}

	public boolean isAuthorizedForCustomer(Integer customerId) throws CustomerException, UserException {

		return customerRepo.findByCustomerIdAndUserUserId(customerId, userInfo.getUserId()).isPresent();

	}

	public boolean isAuthorizedForProduct(Integer productId) throws UserException, ProductException {

		return productRepo.findByProductIdAndUserUserId(productId, userInfo.getUserId()).isPresent();

	}

	public boolean isAuthorizedForVillage(Integer villageId) throws UserException, VillageException {

		return villageRepo.findByVillageIdAndUserUserId(villageId, userInfo.getUserId()).isPresent();

	}

	public User getUserFromContext() throws UserException {

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//		
//		String email = null;
//		
//		System.out.println("auth object is " + auth);
//
//		if (auth != null)
//			System.out.println("principal " + auth.getPrincipal());
//
//		if (auth != null)
//			System.out.println("email " + auth.getName());
//
//	
//		// String email = "kinneramadhu123@gmail.com";
//
//		if(auth!=null)
//			email = auth.getName();
//
////		if (auth != null && !(auth instanceof AnonymousAuthenticationToken))
////			email = auth.getName();
////		else
////			throw new UserException("User Need To Login First");
//
//		System.out.println("The email is " + email);
//
//		User user = userRepo.findByEmail(email)
//				.orElseThrow(() -> new UserException("User Not LoggedIn "));
//
//		System.out.println("The user is " + user);

		return userInfo.getUser();

	}

	public Integer getUserIdFromContext() throws UserException {

		User user = getUserFromContext();
		System.out.println("The User is id" + user.getUserId() + " email " + user.getEmail());

		return user.getUserId();
	}

	@SuppressWarnings("rawtypes")
	public String convertImageToUrl(MultipartFile file) throws IOException {
		Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

		return data.get("secure_url").toString();
	}

	public String encrypt(String plainText) throws Exception {

		Cipher cipher = Cipher.getInstance(ALGORITHM);

		SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedText);
	}

	public String decrypt(String encryptedText) throws Exception {

		Cipher cipher = Cipher.getInstance(ALGORITHM);

		SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText.getBytes());

		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

		return new String(decryptedBytes, StandardCharsets.UTF_8);

	}

}
