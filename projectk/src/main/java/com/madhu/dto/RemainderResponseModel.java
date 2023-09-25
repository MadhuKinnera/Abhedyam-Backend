package com.madhu.dto;

import com.madhu.entity.Customer;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;

import lombok.Data;

@Data
public class RemainderResponseModel {

	private Remainder remainder;

	private Customer customer;

	private SaleRecord record;

}
