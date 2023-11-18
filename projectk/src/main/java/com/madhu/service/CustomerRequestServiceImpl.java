package com.madhu.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.CustomerRequestDTO;
import com.madhu.entity.CustomerRequest;
import com.madhu.exception.CustomerRequestException;
import com.madhu.exception.UserException;
import com.madhu.repository.CustomerRequestRepo;
import com.madhu.utils.CommonUtils;

@Service
public class CustomerRequestServiceImpl implements CustomerRequestService {

	@Autowired
	private CustomerRequestRepo customerRequestRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public CustomerRequest addCustomerRequest(List<String> files, CustomerRequestDTO dto)
			throws IOException, CustomerRequestException, UserException {

		var request = new CustomerRequest();

		request.setUser(utils.getUserFromContext());
		;
		request.setMessage(dto.getMessage());
		request.setTimestamp(LocalDateTime.now());

		// request.setReferenceImages(utils.convertImageToUrl(file));

		List<String> refereceImagesURLs = new ArrayList<>();

		for (String file : files) {

			refereceImagesURLs.add(file);

		}

		request.setReferenceImages(refereceImagesURLs);

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
	public List<CustomerRequest> getCustomerRequestsByUserId(Integer userId)
			throws UserException, CustomerRequestException {

		List<CustomerRequest> requests = customerRequestRepo.findByUserUserId(userId);

		if (requests.isEmpty())
			throw new CustomerRequestException("No Requests Found with User Id " + userId);

		return requests;
	}

}
