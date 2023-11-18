package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.enums.Color;

import lombok.Data;

@Data
public class CustomerResponseModel {

	private Customer customer;

	private List<SaleRecord> saleRecords;

	private Integer totalAmount;

	private List<Product> products;

	private Integer totalProducts;

	private List<Transaction> transactions;

	
	private boolean recordStatus;

	private Integer totalPaidAmount;

	private Integer totalRemaininAmount;
	
	private Color  customerFlag;

	private String description;

}
