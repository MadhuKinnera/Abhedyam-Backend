package com.madhu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.madhu.dto.CustomerRequestDTO;
import com.madhu.entity.CustomerRequest;
import com.madhu.exception.CustomerException;
import com.madhu.exception.CustomerRequestException;

public interface CustomerRequestService {

	CustomerRequest addCustomerRequest(MultipartFile file, CustomerRequestDTO dto) throws CustomerException,CustomerRequestException,IOException;
	
	CustomerRequest updateCustomerRequest(Integer customerRequestId,CustomerRequestDTO dto) throws CustomerRequestException;
	
	CustomerRequest deleteCustomerRequest(Integer customerRequestId) throws CustomerRequestException;
	
	CustomerRequest getCustomerRequestByCRId(Integer customerRequestId) throws CustomerRequestException;
	
	List<CustomerRequest> getCustomerRequestsByCustomerId(Integer customerId) throws CustomerException,CustomerRequestException;
	

}
