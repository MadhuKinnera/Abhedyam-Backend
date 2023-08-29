package com.madhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.Remainder;
import com.madhu.exception.RemainderException;
import com.madhu.repository.RemainderRepo;
import com.madhu.utils.CommonUtils;

@Service
public class RemainderServiceImpl implements RemainderService {

	@Autowired
	private RemainderRepo remainderRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Remainder addRemainder(Remainder remainder) throws RemainderException {

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
