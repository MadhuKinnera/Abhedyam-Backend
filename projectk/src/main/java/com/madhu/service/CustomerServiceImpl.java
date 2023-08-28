package com.madhu.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.CustomerResponseModel;
import com.madhu.dto.FirstCustomerDTO;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.RecordException;
import com.madhu.exception.VillageException;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.VillageRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

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

	@Override
	public Customer addCustomer(Customer customer) {

		return customerRepo.save(customer);

	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {

		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId));
	}

	@Override
	public Customer updateCustomer(Integer customerId, Customer customer) throws CustomerException {

		if (!utils.isCustomerExist(customerId))
			throw new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId);

		return customerRepo.save(customer);

	}

	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException {

		Customer customer = getCustomerById(customerId);

		customerRepo.delete(customer);

		return customer;
	}

	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) throws CustomerException {

		return customerRepo.findByMobileNo(phoneNumber)
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_PHONE_NOT_FOUND + phoneNumber));

	}

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerException {
		return customerRepo.findByEmail(email)
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_EMAIL_NOT_FOUND + email));
	}

	@Override
	public List<Customer> getCustomersByKeyword(String keyword) throws CustomerException {

		List<Customer> customerList = customerRepo.findByKeywordsContaining(keyword);

		if (customerList.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customerList;
	}

	@Override
	public Customer getCustomersByAddressId(Integer addressId) throws CustomerException, AddressException {

		return customerRepo.findByAddressAddressId(addressId)
				.orElseThrow(() -> new AddressException(Constants.ADDRESS_ID_NOT_FOUND + addressId));
	}

	@Override
	public List<Customer> getCustomersByAddressVillageId(Integer villageId) throws CustomerException, VillageException {

		if (!utils.isVillageExist(villageId))
			throw new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId);

		List<Customer> customers = customerRepo.findByAddressVillageVillageId(villageId);

		if (customers.isEmpty())
			throw new CustomerException(Constants.NO_CUSTOMERS_IN_THE_VILLAGE + villageId);

		return customers;

	}

	@Override
	public List<Customer> getCustomersByAddressVillageName(String villageName)
			throws CustomerException, VillageException {

		if (!utils.isVillageExistByName(villageName))
			throw new VillageException(Constants.VILLAGE_NAME_NOT_FOUND + villageName);

		List<Customer> customers = customerRepo.findByAddressVillageVillageName(villageName);

		if (customers.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customers;
	}

	@Override
	public List<Customer> getCustomersByAddressPincode(Integer pincode) throws CustomerException, VillageException {

		if (!utils.isPincodeExist(pincode))
			throw new VillageException(Constants.PINCODE_NOT_FOUND + pincode);

		List<Customer> customers = customerRepo.findByAddressVillagePincode(pincode);

		if (customers.isEmpty())
			throw new CustomerException(Constants.CUSTOMERS_NOT_FOUND);

		return customers;

	}

	@Override
	public List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, RecordException {

		if (!utils.isCustomerExist(customerId))
			throw new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + customerId);

		Customer customer = getCustomerById(customerId);

		if (!customer.getSaleRecords().isEmpty())
			throw new RecordException(Constants.NO_RECORDS_FOUND_WITH_CUSTOMER_ID + customerId);

		return customer.getSaleRecords();
	}

	@Override
	public List<Customer> getAllCustomersAgeGreaterThan(Integer age) throws CustomerException {

		List<Customer> customers = customerRepo.findByAgeGreaterThan(age);

		if (customers.isEmpty())
			throw new CustomerException("No Customers Found With Age Greater Than " + age);

		return customers;

	}

	@Override
	public List<Customer> getAllCustomersAgeLessThan(Integer age) throws CustomerException {
		List<Customer> customers = customerRepo.findByAgeLessThan(age);

		if (customers.isEmpty())
			throw new CustomerException("No Customers Found With Age Less Than " + age);

		return customers;
	}

	@Override
	public List<CustomerResponseModel> getCustomersByRank() throws CustomerException {

		List<Customer> customers = customerRepo.findAll();

		if (customers.isEmpty())
			throw new CustomerException(" No Customers Found ");

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

			List<Transaction> transactions = new ArrayList<>();

			for (SaleRecord record : customer.getSaleRecords()) {
				Product product = record.getProduct();
				products.add(product);
				totalProducts += record.getQuantity();
				totalAmount += record.getTotalAmount();
				totalDueAmount += record.getDueAmount();

				transactions.addAll(record.getTransactions());

			}

			model.setProducts(products);

			model.setRecordStatus(totalProducts != 0);

			model.setTotalAmount(totalAmount);

			model.setTotalRemaininAmount(totalDueAmount);

			model.setTotalPaidAmount(totalAmount - totalDueAmount);

			model.setTransactions(transactions);

			customerResponseModels.add(model);

		}

		return customerResponseModels;
	}

	@Override
	public Customer addFirstCustomer(FirstCustomerDTO firstCustomer) throws VillageException, ProductException {

		Customer customer = new Customer();

		Product product = productRepo.findByProductName(firstCustomer.getProductName()).orElseThrow(
				() -> new ProductException(Constants.PRODUCT_NAME_NOT_FOUND + firstCustomer.getCustomerName()));

		Village village = villageRepo.findByVillageName(firstCustomer.getVillageName()).orElseThrow(
				() -> new VillageException(Constants.VILLAGE_NAME_NOT_FOUND + firstCustomer.getVillageName()));

		customer.getAddress().setVillage(village);

		SaleRecord record = new SaleRecord();

		record.setAddress(customer.getAddress());
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
	public Customer updateProfilePicture(MultipartFile file) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddressOfCustomer(Integer customerId) throws AddressException, CustomerException {
		Customer customer = getCustomerById(customerId);

		if (customer.getAddress().getAddressId() == null)
			throw new AddressException("Address Not Found with the Customer Id  " + customerId);

		return customer.getAddress();
	}

}
