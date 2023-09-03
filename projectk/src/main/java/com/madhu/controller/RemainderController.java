package com.madhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhu.dto.GeneralResponse;
import com.madhu.dto.RemainderDTO;
import com.madhu.entity.Remainder;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.service.RemainderService;

@RestController
@RequestMapping("/remainder")
public class RemainderController {

	@Autowired
	private RemainderService remainderService;

	@PostMapping("/addRemainder")
	ResponseEntity<GeneralResponse> addRemainder(@RequestBody RemainderDTO remainder) throws RemainderException, RecordException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Added ");
		generalResponse.setData(remainderService.addRemainder(remainder));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRemainder/{remainderId}")
	ResponseEntity<GeneralResponse> getRemainderById(@PathVariable Integer remainderId) throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Found with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.getRemainderById(remainderId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateRemainder/{remainderId}")
	ResponseEntity<GeneralResponse> updateRemainder(@PathVariable Integer remainderId,@RequestBody Remainder remainder)
			throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Updated with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.updateRemainder(remainderId, remainder));

		return ResponseEntity.ok(generalResponse);
	}

	@DeleteMapping("/deleteRemainder/{remainderId}")
	ResponseEntity<GeneralResponse> deleteRemainderById(@PathVariable Integer remainderId) throws RemainderException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Remainder Deleted with Remainder Id " + remainderId);
		generalResponse.setData(remainderService.deleteRemainderById(remainderId));

		return ResponseEntity.ok(generalResponse);
	}

}
