package com.madhu.service;

import java.io.IOException;
import java.util.List;

import com.madhu.dto.CustomerRequestDTO;
import com.madhu.entity.CustomerRequest;
import com.madhu.exception.CustomerRequestException;
import com.madhu.exception.UserException;

public interface CustomerRequestService {

	CustomerRequest addCustomerRequest(List<String> files, CustomerRequestDTO dto) throws UserException,CustomerRequestException,IOException;
	
	CustomerRequest updateCustomerRequest(Integer customerRequestId,CustomerRequestDTO dto) throws CustomerRequestException;
	
	CustomerRequest deleteCustomerRequest(Integer customerRequestId) throws CustomerRequestException;
	
	CustomerRequest getCustomerRequestByCRId(Integer customerRequestId) throws CustomerRequestException;
	
	List<CustomerRequest> getCustomerRequestsByUserId(Integer userId) throws UserException,CustomerRequestException;
	

}
