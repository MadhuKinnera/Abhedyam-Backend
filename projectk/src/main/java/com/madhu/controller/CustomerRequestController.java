package com.madhu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhu.dto.CustomerRequestDTO;
import com.madhu.dto.GeneralResponse;
import com.madhu.exception.CustomerRequestException;
import com.madhu.exception.UserException;
import com.madhu.service.CustomerRequestService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin("*")
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/customerRequest")
public class CustomerRequestController {

	@Autowired
	private CustomerRequestService customerRequestService;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/addCustomerRequest")
	ResponseEntity<GeneralResponse> addCustomerRequest(@RequestParam("image[]") List<String> files,
			@RequestParam("data") String dtoData) throws CustomerRequestException, IOException, UserException {
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

	@GetMapping("/getCustomerRequestsByUserId/{userId}")
	ResponseEntity<GeneralResponse> getCustomerRequestsByCustomerId(@PathVariable Integer userId)
			throws CustomerRequestException, UserException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Customer Requests Found with Customer Id " + userId);
		generalResponse.setData(customerRequestService.getCustomerRequestsByUserId(userId));

		return ResponseEntity.ok(generalResponse);

	}

}
