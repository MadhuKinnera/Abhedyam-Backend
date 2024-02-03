package com.madhu.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.AddressDTO;
import com.madhu.dto.CustomerDTO;
import com.madhu.dto.CustomerPersonalDto;
import com.madhu.dto.CustomerResponseModel;
import com.madhu.dto.FirstCustomerDTO;
import com.madhu.dto.NameAndId;
import com.madhu.dto.PlainCustomer;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.enums.Color;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.RecordRepo;
import com.madhu.repository.VillageRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;
import com.madhu.utils.UserInfo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CommonUtils utils;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private VillageRepo villageRepo;

	@Autowired
	private RecordRepo recordRepo;
	
	@Autowired
	private UserInfo userInfo;

	@Override
	public Customer addCustomer(CustomerDTO dto) throws CustomerException, UserException, IOException {

		var customer = new Customer();

		Address address = null;

		customer.setUser(utils.getUserFromContext());

		if (dto.getProfileImage() != null) {
			String imageUrl = dto.getProfileImage();
			customer.setProfileImageUrl(imageUrl);
		}
		customer.setAge(dto.getAge());
		customer.setCustomerName(dto.getCustomerName());
		customer.setDescription(dto.getDescription());
		customer.setEmail(dto.getEmail());
		customer.setFlag(Color.GREEN);
		customer.setProfession(dto.getProfession());
		customer.setMobileNo(dto.getMobileNo());

		var keywords = dto.getKeywords();
		customer.setKeywords(keywords);

		if (dto.getAddressDto() != null) {
			address = new Address();

			Village village = villageRepo
					.findByVillageNameAndUserUserId(dto.getAddressDto().getVillageName(), userInfo.getUserId())
					.orElseThrow(() -> new CustomerException("Invalid Village Name Provided"));

			address.setCustomer(customer);
			address.setDescription(dto.getAddressDto().getDescription());
			address.setStreet(dto.getAddressDto().getStreet());
			address.setLandmark(dto.getAddressDto().getLandmark());
			address.setVillage(village);
			customer.setAddress(address);
		}

		var customerCode = getCustomerCode(dto);

		customer.setCustomerCode(customerCode);

		return customerRepo.save(customer);

	}

	private String getCustomerCode(CustomerDTO customerDto) throws UserException {

		var user = utils.getUserFromContext();

		var userName = user.getFullName();

		if (userName == null)
			userName = "USER";

		var customerName = customerDto.getCustomerName();

		if (customerName == null)
			customerName = "CUSTOMER";

		var villageName = customerDto.getAddressDto().getVillageName();

		if (villageName == null)
			villageName = "VILLAGE";

		var uuid = UUID.randomUUID().toString();

		if (uuid.length() > 10) {
			uuid = uuid.substring(2, 8);
		}

		var customerCode = userName.substring(0, 3) + customerName.subSequence(0, 3) + uuid;

		return customerCode;

	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId));
	}

	@Override
	public Customer updateCustomer(Integer customerId, Customer customer) throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		if (!utils.isCustomerExist(customerId))
			throw new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId);

		return customerRepo.save(customer);

	}

	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		Customer customer = getCustomerById(customerId);

		customerRepo.delete(customer);

		return customer;
	}

	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) throws CustomerException, UserException {

		Customer customer = customerRepo.findByMobileNoAndUserUserId(phoneNumber, userInfo.getUserId())
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_PHONE_NOT_FOUND + phoneNumber));

		utils.isAuthorizedForCustomer(customer.getCustomerId());

		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerException, UserException {
		Customer customer = customerRepo.findByEmailAndUserUserId(email, userInfo.getUserId())
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_EMAIL_NOT_FOUND + email));

		utils.isAuthorizedForCustomer(customer.getCustomerId());

		return customer;
	}

	@Override
	public List<Customer> getCustomersByKeyword(String keyword) throws CustomerException {

		List<Customer> customerList = customerRepo.findByKeywordsContainingAndUserUserId(keyword, userInfo.getUserId());

		if (customerList.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customerList;
	}

	@Override
	public Customer getCustomersByAddressId(Integer addressId) throws CustomerException, AddressException {

		return customerRepo.findByAddressAddressIdAndUserUserId(addressId, userInfo.getUserId())
				.orElseThrow(() -> new AddressException(Constants.ADDRESS_ID_NOT_FOUND + addressId));
	}

	@Override
	public List<Customer> getCustomersByAddressVillageId(Integer villageId) throws CustomerException, VillageException {

		if (!utils.isVillageExist(villageId))
			throw new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId);

		List<Customer> customers = customerRepo.findByAddressVillageVillageIdAndUserUserId(villageId, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException(Constants.NO_CUSTOMERS_IN_THE_VILLAGE + villageId);

		return customers;

	}

	@Override
	public List<Customer> getCustomersByAddressVillageName(String villageName)
			throws CustomerException, VillageException {

		if (!utils.isVillageExistByName(villageName))
			throw new VillageException(Constants.VILLAGE_NAME_NOT_FOUND + villageName);

		List<Customer> customers = customerRepo.findByAddressVillageVillageNameAndUserUserId(villageName, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customers;
	}

	@Override
	public List<Customer> getCustomersByAddressPincode(Integer pincode) throws CustomerException, VillageException {

		if (!utils.isPincodeExist(pincode))
			throw new VillageException(Constants.PINCODE_NOT_FOUND + pincode);

		List<Customer> customers = customerRepo.findByAddressVillagePincodeAndUserUserId(pincode, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customers;

	}

	@Override
	public List<Customer> getAllCustomersAgeGreaterThan(Integer age) throws CustomerException {

		List<Customer> customers = customerRepo.findByAgeGreaterThanAndUserUserId(age, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException("No Customers Found With Age Greater Than " + age);

		return customers;

	}

	@Override
	public List<Customer> getAllCustomersAgeLessThan(Integer age) throws CustomerException {
		List<Customer> customers = customerRepo.findByAgeLessThanAndUserUserId(age, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException("No Customers Found With Age Less Than " + age);

		return customers;
	}

	@Override
	public Customer addFirstCustomer(FirstCustomerDTO firstCustomer) throws VillageException, ProductException {

		Customer customer = new Customer();

		Product product = productRepo.findByProductNameAndUserUserId(firstCustomer.getProductName(), userInfo.getUserId())
				.orElseThrow(
						() -> new ProductException(Constants.PRODUCT_NAME_NOT_FOUND + firstCustomer.getCustomerName()));

		Village village = villageRepo.findByVillageNameAndUserUserId(firstCustomer.getVillageName(), userInfo.getUserId())
				.orElseThrow(
						() -> new VillageException(Constants.VILLAGE_NAME_NOT_FOUND + firstCustomer.getVillageName()));

		customer.getAddress().setVillage(village);

		SaleRecord record = new SaleRecord();

		record.setCustomer(customer);
		record.setTotalAmount(firstCustomer.getAmount());
		record.setDueAmount(firstCustomer.getAmount());
		record.setStartDate(LocalDate.now());
		record.setProduct(product);

		customer.setCustomerName(firstCustomer.getCustomerName());

		customer.setSaleRecords(List.of(record));

		return customer;

	}

	@Override
	public Customer updateProfilePicture(Integer customerId, String file)
			throws CustomerException, IOException, UserException {

		Customer customer = getCustomerById(customerId);

		String imageURL = file;

		customer.setProfileImageUrl(imageURL);

		return customerRepo.save(customer);
	}

	@Override
	public Address getAddressOfCustomer(Integer customerId) throws AddressException, CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		Customer customer = getCustomerById(customerId);

		if (customer.getAddress().getAddressId() == null)
			throw new AddressException("Address Not Found with the Customer Id  " + customerId);

		return customer.getAddress();
	}

	@Override
	public Customer updateAddressToACustomer(AddressDTO dto, Integer customerId)
			throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		Customer customer = getCustomerById(customerId);

		var address = new Address();

		Village village = villageRepo.findByVillageNameAndUserUserId(dto.getVillageName(), userInfo.getUserId())
				.orElseThrow(() -> new CustomerException("Village Not Found with Name " + dto.getVillageName()));
		address.setCustomer(customer);
		address.setDescription(dto.getDescription());
		address.setLandmark(dto.getLandmark());
		address.setStreet(dto.getStreet());
		address.setVillage(village);

		customer.setAddress(address);

		return customerRepo.save(customer);
	}

	@Override
	public Customer updateFlagOfACustomer(Integer customerId, Color flag) throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		Customer customer = getCustomerById(customerId);

		customer.setFlag(flag);

		return customerRepo.save(customer);
	}

	@Override
	public Customer addKeywordsToCustomer(Integer customerId, List<String> keywords)
			throws CustomerException, UserException {

		utils.isAuthorizedForCustomer(customerId);

		Customer customer = getCustomerById(customerId);

		customer.getKeywords().addAll(keywords);

		return customerRepo.save(customer);
	}

	@Override
	public List<CustomerResponseModel> getCustomersByRank() throws CustomerException, UserException {

		List<Customer> customers = customerRepo.findByUserUserId(userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException(" Customers Not Found ");

		List<CustomerResponseModel> customerResponseModels = new ArrayList<>();

		for (Customer customer : customers) {

			CustomerResponseModel model = new CustomerResponseModel();

			model.setCustomer(customer);
			model.setCustomerFlag(customer.getFlag());
			model.setDescription(customer.getDescription());

			List<Product> products = new ArrayList<>();

			Integer totalProducts = 0;
			Integer totalAmount = 0;
			Integer totalDueAmount = 0;

			List<SaleRecord> records = recordRepo
					.findByCustomerCustomerIdAndCustomerUserUserId(customer.getCustomerId(), userInfo.getUserId());

			List<Transaction> transactions = new ArrayList<>();

			for (SaleRecord record : records) {
				Product product = record.getProduct();
				products.add(product);
				totalProducts += record.getQuantity();
				totalAmount += record.getTotalAmount();
				totalDueAmount += record.getDueAmount();

				transactions.addAll(record.getTransactions());

			}

			model.setProducts(products);

			model.setRecordStatus(totalProducts != 0);

			model.setTotalProducts(totalProducts);

			model.setTotalAmount(totalAmount);

			model.setTotalRemaininAmount(totalDueAmount);

			model.setTotalPaidAmount(totalAmount - totalDueAmount);

			model.setTransactions(transactions);

			model.setSaleRecords(records);

			customerResponseModels.add(model);

		}

		Collections.sort(customerResponseModels, (c1, c2) -> Integer.compare(c2.getTotalAmount(), c1.getTotalAmount()));

		return customerResponseModels;
	}

	@Override
	public List<PlainCustomer> getPlainCustomers() throws UserException {

		List<Customer> customers = customerRepo.findByUserUserId(userInfo.getUserId());

		if (customers.isEmpty())
			throw new UserException("Customers Not Found With the User");

		List<PlainCustomer> plainCustomers = new ArrayList<>();

		customers.stream().forEach(c -> {
			var p = new PlainCustomer();
			p.setCustomerId(c.getCustomerId());
			p.setAge(c.getAge());
			p.setCustomerName(c.getCustomerName());
			p.setDescription(c.getDescription());
			p.setEmail(c.getEmail());
			p.setFlag(c.getFlag());
			p.setMobileNo(c.getMobileNo());
			p.setProfession(c.getProfession());
			p.setProfileImageUrl(c.getProfileImageUrl());
			p.setCustomerCode(c.getCustomerCode());
			plainCustomers.add(p);
		});

		return plainCustomers;
	}

	@Override
	public CustomerResponseModel getCustomerResponseModelByCustomerId(Integer customerId)
			throws UserException, CustomerException {

		var customer = customerRepo.findByCustomerIdAndUserUserId(customerId, userInfo.getUserId())
				.orElseThrow(() -> new CustomerException("Customer Not Found With Customer Id " + customerId));
		var model = new CustomerResponseModel();

		model.setCustomer(customer);
		model.setCustomerFlag(customer.getFlag());
		model.setDescription(customer.getDescription());

		List<Product> products = new ArrayList<>();

		Integer totalProducts = 0;
		Integer totalAmount = 0;
		Integer totalDueAmount = 0;

		List<SaleRecord> records = recordRepo.findByCustomerCustomerIdAndCustomerUserUserId(customer.getCustomerId(),
				userInfo.getUserId());

		List<Transaction> transactions = new ArrayList<>();

		for (SaleRecord record : records) {
			Product product = record.getProduct();
			products.add(product);
			totalProducts += record.getQuantity();
			totalAmount += record.getTotalAmount();
			totalDueAmount += record.getDueAmount();

			transactions.addAll(record.getTransactions());

		}

		model.setProducts(products);

		model.setRecordStatus(totalProducts != 0);

		model.setTotalProducts(totalProducts);

		model.setTotalAmount(totalAmount);

		model.setTotalRemaininAmount(totalDueAmount);

		model.setTotalPaidAmount(totalAmount - totalDueAmount);

		model.setTransactions(transactions);

		model.setSaleRecords(records);

		return model;
	}

	@Override
	public List<CustomerResponseModel> getCustomersByCustomerNameContaining(String customerName)
			throws CustomerException, UserException {
		List<Customer> customers = customerRepo.findByCustomerNameContainingAndUserUserId(customerName, userInfo.getUserId());

		if (customers.isEmpty())
			throw new CustomerException(" Customers Not Found with Name " + customerName);

		List<CustomerResponseModel> customerResponseModels = new ArrayList<>();

		for (Customer customer : customers) {

			CustomerResponseModel model = new CustomerResponseModel();

			model.setCustomer(customer);
			model.setCustomerFlag(customer.getFlag());
			model.setDescription(customer.getDescription());

			List<Product> products = new ArrayList<>();

			Integer totalProducts = 0;
			Integer totalAmount = 0;
			Integer totalDueAmount = 0;

			List<SaleRecord> records = recordRepo
					.findByCustomerCustomerIdAndCustomerUserUserId(customer.getCustomerId(), userInfo.getUserId());

			List<Transaction> transactions = new ArrayList<>();

			for (SaleRecord record : records) {
				Product product = record.getProduct();
				products.add(product);
				totalProducts += record.getQuantity();
				totalAmount += record.getTotalAmount();
				totalDueAmount += record.getDueAmount();

				transactions.addAll(record.getTransactions());

			}

			model.setProducts(products);

			model.setRecordStatus(totalProducts != 0);

			model.setTotalProducts(totalProducts);

			model.setTotalAmount(totalAmount);

			model.setTotalRemaininAmount(totalDueAmount);

			model.setTotalPaidAmount(totalAmount - totalDueAmount);

			model.setTransactions(transactions);

			model.setSaleRecords(records);

			customerResponseModels.add(model);

		}

		return customerResponseModels;
	}

	@Override
	public List<NameAndId> getCustomersName() throws CustomerException, UserException {
		var customers = getPlainCustomers();

		if (customers.isEmpty())
			throw new CustomerException("Customes Not Found");

		var customersNames = new ArrayList<NameAndId>();

		for (var c : customers) {
			var name = new NameAndId(c.getCustomerName(), c.getCustomerId());
			customersNames.add(name);
		}

		return customersNames;
	}

	@Override
	public CustomerPersonalDto getCustomerPersonalDetails(String customerCode) throws CustomerException {

		Customer customer = customerRepo.findByCustomerCode(customerCode)
				.orElseThrow(() -> new CustomerException("Invalid Customer Code " + customerCode));

		var customerPersonalDto = new CustomerPersonalDto();

		customer.setDescription(null);
		customer.setFlag(Color.GREEN);
		customer.setKeywords(new ArrayList<String>());

		customerPersonalDto.setCustomer(customer);

		var user = customer.getUser();

		customerPersonalDto.setCreditorName(user.getFullName());
		customerPersonalDto.setCreditorPhoneNumber(user.getPhoneNumber());
		customerPersonalDto.setCreditorQRImageUrl(user.getQrImageUrl());
		customerPersonalDto.setCreditorProfileImageUrl(user.getProfileImageUrl());
		

		var records = recordRepo.findByCustomerCustomerId(customer.getCustomerId());

		customerPersonalDto.setSaleRecords(records);

		var totalDueAmount = records.stream().mapToInt(r -> r.getDueAmount().intValue()).sum();

		customerPersonalDto.setRecordStatus(totalDueAmount > 0);

		var totalAmount = records.stream().mapToInt(SaleRecord::getTotalAmount).sum();

		List<String> productNames = new ArrayList<>();

		List<Transaction> transactions = new ArrayList<>();

		var totalProducts = 0;

		for (SaleRecord record : records) {
			Product product = record.getProduct();

			product.setBuyedPrice(0);
			product.setDescription(null);
			product.setProductId(0);

			productNames.add(product.getProductName());
			totalProducts += record.getQuantity();

			List<Transaction> tempTransactions = record.getTransactions();

			transactions.addAll(tempTransactions);

		}

		customerPersonalDto.setProductNames(productNames);

		customerPersonalDto.setRecordStatus(totalProducts != 0);

		customerPersonalDto.setTotalProducts(totalProducts);

		customerPersonalDto.setTotalAmount(totalAmount);

		customerPersonalDto.setTotalRemaininAmount(totalDueAmount);

		customerPersonalDto.setTotalPaidAmount(totalAmount - totalDueAmount);

		customerPersonalDto.setTransactions(transactions);

		customerPersonalDto.setTotalTransactions(transactions.size());

		customerPersonalDto.setSaleRecords(records);

		customerPersonalDto.setTotalRecords(records.size());

		return customerPersonalDto;

	}

}
