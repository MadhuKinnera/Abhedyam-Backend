package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.entity.Remainder;
import com.madhu.exception.RemainderException;
import com.madhu.service.RemainderService;

@RestController
@RequestMapping("/remainder")
public class RemainderController {

	@Autowired
	private RemainderService remainderService;

	ResponseEntity<GeneralResponse> addRemainder(Remainder remainder) throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Added ");
		generalResponse.setData(remainderService.addRemainder(remainder));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getRemainderById(Integer remainderId) throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Found with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.getRemainderById(remainderId));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> updateRemainder(Integer remainderId, Remainder remainder)
			throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Updated with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.updateRemainder(remainderId, remainder));

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> deleteRemainderById(Integer remainderId) throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Deleted with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.deleteRemainderById(remainderId));

		return ResponseEntity.ok(generalResponse);
	}

}
