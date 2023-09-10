package com.madhu.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.CustomerRequestDTO;
import com.madhu.entity.Customer;
import com.madhu.entity.CustomerRequest;
import com.madhu.exception.CustomerException;
import com.madhu.exception.CustomerRequestException;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.CustomerRequestRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class CustomerRequestServiceImpl implements CustomerRequestService {

	@Autowired
	private CustomerRequestRepo customerRequestRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public CustomerRequest addCustomerRequest(MultipartFile file, CustomerRequestDTO dto)
			throws CustomerException, IOException, CustomerRequestException {

		Customer customer = customerRepo.findById(dto.getCustomerId())
				.orElseThrow(() -> new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + dto.getCustomerId()));

		var request = new CustomerRequest();

		request.setCustomer(customer);
		request.setMessage(dto.getMessage());
		request.setTimestamp(LocalDateTime.now());

		request.setReferenceImages(utils.convertImageToUrl(file));

		return customerRequestRepo.save(request);
	}

	@Override
	public CustomerRequest updateCustomerRequest(Integer customerRequestId, CustomerRequestDTO dto)
			throws CustomerRequestException {

		CustomerRequest request = getCustomerRequestByCRId(customerRequestId);

		request.setMessage(dto.getMessage());
		return customerRequestRepo.save(request);

	}

	@Override
	public CustomerRequest deleteCustomerRequest(Integer customerRequestId) throws CustomerRequestException {

		CustomerRequest request = getCustomerRequestByCRId(customerRequestId);

		customerRequestRepo.delete(request);

		return request;
	}

	@Override
	public CustomerRequest getCustomerRequestByCRId(Integer customerRequestId) throws CustomerRequestException {

		return customerRequestRepo.findById(customerRequestId).orElseThrow(() -> new CustomerRequestException(
				"Customer Request Not Found with Customer Request Id " + customerRequestId));
	}

	@Override
	public List<CustomerRequest> getCustomerRequestsByCustomerId(Integer customerId)
			throws CustomerException, CustomerRequestException {

		List<CustomerRequest> requests = customerRequestRepo.findByCustomerCustomerId(customerId);

		if (requests.isEmpty())
			throw new CustomerRequestException("No Requests Found with Customer Id " + customerId);

		return requests;
	}

}
