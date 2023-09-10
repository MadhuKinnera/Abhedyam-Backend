package com.madhu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhu.dto.CustomerRequestDTO;
import com.madhu.dto.GeneralResponse;
import com.madhu.exception.CustomerException;
import com.madhu.exception.CustomerRequestException;
import com.madhu.service.CustomerRequestService;

@RestController
@RequestMapping("/customerRequest")
public class CustomerRequestController {

	@Autowired
	private CustomerRequestService customerRequestService;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/addCustomerRequest")
	ResponseEntity<GeneralResponse> addCustomerRequest(@RequestParam("image[]") List<MultipartFile> files,
			@RequestParam("data") String dtoData) throws CustomerException, CustomerRequestException, IOException {
		var generalResponse = new GeneralResponse();

		CustomerRequestDTO dto = mapper.readValue(dtoData, CustomerRequestDTO.class);

		generalResponse.setMessage("Customer Request Added ");
		generalResponse.setData(customerRequestService.addCustomerRequest(files, dto));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateCustomerRequest/{customerRequestId}")
	ResponseEntity<GeneralResponse> updateCustomerRequest(@PathVariable Integer customerRequestId,
			@RequestBody CustomerRequestDTO dto) throws CustomerRequestException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Request Updated with Customer Request Id " + customerRequestId);
		generalResponse.setData(customerRequestService.updateCustomerRequest(customerRequestId, dto));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteCustomerRequest/{customerRequestId}")
	ResponseEntity<GeneralResponse> deleteCustomerRequest(@PathVariable Integer customerRequestId)
			throws CustomerRequestException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Request Deleted with Customer Request Id " + customerRequestId);
		generalResponse.setData(customerRequestService.deleteCustomerRequest(customerRequestId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getCustomerRequestByCustomerRequestId/{customerRequestId}")
	ResponseEntity<GeneralResponse> getCustomerRequestByCRId(@PathVariable Integer customerRequestId)
			throws CustomerRequestException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Request Found with Customer Request Id " + customerRequestId);
		generalResponse.setData(customerRequestService.getCustomerRequestByCRId(customerRequestId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getCustomerRequestsByCustomerId/{customerId}")
	ResponseEntity<GeneralResponse> getCustomerRequestsByCustomerId(@PathVariable Integer customerId)
			throws CustomerException, CustomerRequestException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Requests Found with Customer Id " + customerId);
		generalResponse.setData(customerRequestService.getCustomerRequestsByCustomerId(customerId));

		return ResponseEntity.ok(generalResponse);

	}

}
