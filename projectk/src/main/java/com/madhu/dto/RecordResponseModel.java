package com.madhu.dto;

import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Village;

import lombok.Data;

@Data
public class RecordResponseModel {

	private SaleRecord saleRecord;
	private Customer customer;
	private Village village;

}
