package com.madhu.service;

import com.madhu.dto.RemainderDTO;
import com.madhu.entity.Remainder;
import com.madhu.exception.RecordException;
import com.madhu.exception.RemainderException;

public interface RemainderService {
	
	 Remainder addRemainder(RemainderDTO dto) throws RemainderException,RecordException;
	 
	 Remainder getRemainderById(Integer remainderId)throws RemainderException;

	 Remainder updateRemainder(Integer remainderId,Remainder remainder) throws RemainderException;
	 
	 Remainder deleteRemainderById(Integer remainderId) throws RemainderException;
	 	 	 
}
