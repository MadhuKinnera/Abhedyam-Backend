package com.madhu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.RemainderDTO;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.UserException;
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

	@Override
	public List<Remainder> getRemaindersByRecordId(Integer recordId) throws RecordException, RemainderException {

		List<Remainder> remainders = remainderRepo.findBySaleRecordRecordId(recordId);

		if (remainders.isEmpty())
			throw new RecordException(Constants.RECORD_ID_NOT_FOUND + recordId);

		return remainders;
	}

	@Override
	public List<Remainder> getRemaindersByUserId(Integer userId) throws UserException, RemainderException {

		List<Remainder> remainders = remainderRepo.findBySaleRecordCustomerUserUserId(userId);

		if (remainders.isEmpty())
			throw new UserException("Remainders Not Found with User Id" + userId);

		return remainders;

	}

	@Override
	public List<Remainder> getRemainderByDateAndUserId(LocalDate startDate, LocalDate endDate, Integer userId)
			throws RemainderException {
		List<Remainder> remainders = remainderRepo.findByRemainderDateBetweenAndSaleRecordCustomerUserUserId(startDate,
				endDate, userId);

		if (remainders.isEmpty())
			throw new RemainderException(
					"Remainders Not Found with User Id " + userId + " Between " + startDate + " and " + endDate);

		return remainders;
	}

	@Override
	public List<Remainder> getRemaindersByCustomerId(Integer customerId) throws RemainderException, CustomerException {
		List<Remainder> remainders = remainderRepo.findBySaleRecordCustomerCustomerId(customerId);

		if (remainders.isEmpty())
			throw new RemainderException("Remainders Not Found with Customer Id " + customerId);

		return remainders;
	}

}
