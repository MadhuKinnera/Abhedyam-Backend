package com.madhu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.RemainderDTO;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.repository.RecordRepo;
import com.madhu.repository.RemainderRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class RemainderServiceImpl implements RemainderService {

	@Autowired
	private RemainderRepo remainderRepo;

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Remainder addRemainder(RemainderDTO dto) throws RemainderException, RecordException {

		var remainder = new Remainder();

		SaleRecord record = recordRepo.findById(dto.getRecordId())
				.orElseThrow(() -> new RecordException(Constants.RECORD_ID_NOT_FOUND + dto.getRecordId()));

		remainder.setCreatedTimestamp(LocalDateTime.now());
		remainder.setDescription(dto.getDescription());
		remainder.setRemainderDate(LocalDate.now());
		remainder.setRemainderMessage(dto.getRemainderMessage());
		remainder.setSaleRecord(record);
		remainder.setRemainderDateTime(dto.getRemainderDateTime());

		return remainderRepo.save(remainder);
	}

	@Override
	public Remainder getRemainderById(Integer remainderId) throws RemainderException {

		return remainderRepo.findById(remainderId)
				.orElseThrow(() -> new RemainderException("No Remainder Found with Remainder Id " + remainderId));

	}

	@Override
	public Remainder updateRemainder(Integer remainderId, Remainder remainder) throws RemainderException {

		if (!utils.isRemainderExist(remainderId))
			throw new RemainderException("Remainder Not Found with Remainder Id " + remainderId);

		return remainderRepo.save(remainder);
	}

	@Override
	public Remainder deleteRemainderById(Integer remainderId) throws RemainderException {

		Remainder remainder = getRemainderById(remainderId);

		remainderRepo.delete(remainder);

		return remainder;
	}

}
