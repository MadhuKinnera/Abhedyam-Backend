package com.madhu.dto;

import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;

import lombok.Data;


@Data
public class TransactionResponeModel {

	private Transaction transaction;

	private Customer customer;

	private Product product;
	
	private SaleRecord saleRecord;	
	
	

}
