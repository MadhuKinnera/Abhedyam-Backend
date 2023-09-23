package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

import com.madhu.dto.RemainderDTO;
import com.madhu.entity.Remainder;
import com.madhu.exception.CustomerException;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.UserException;

public interface RemainderService {
	
	 Remainder addRemainder(RemainderDTO dto) throws RemainderException,RecordException;
	 
	 Remainder getRemainderById(Integer remainderId)throws RemainderException;

	 Remainder updateRemainder(Integer remainderId,Remainder remainder) throws RemainderException;
	 
	 Remainder deleteRemainderById(Integer remainderId) throws RemainderException;
	 
	 //added 03 sept
	 
	 List<Remainder> getRemaindersByRecordId(Integer recordId) throws RecordException,RemainderException;
	 
	 List<Remainder> getRemaindersByUserId(Integer userId) throws UserException,RemainderException;
	 
	 List<Remainder> getRemainderByDateAndUserId(LocalDate startDate , LocalDate endDate,Integer userId) throws RemainderException;
	 
	 List<Remainder> getRemaindersByCustomerId(Integer customerId) throws RemainderException,CustomerException;
	 	 	 
}
