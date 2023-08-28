package com.madhu.service;

import com.madhu.entity.Remainder;
import com.madhu.exception.RemainderException;

public interface RemainderService {
	
	 Remainder addRemainder(Remainder remainder) throws RemainderException;
	 
	 Remainder getRemainderById(Integer remainderId)throws RemainderException;

	 Remainder updateRemainder(Integer remainderId,Remainder remainder) throws RemainderException;
	 
	 Remainder deleteRemainderById(Integer remainderId) throws RemainderException;
	 	 	 
}
