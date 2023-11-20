package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;

import lombok.Data;

@Data
public class CustomerPersonalDto {

	private Customer customer;

	private String creditorName;

	private String creditorPhoneNumber;
	
	private String creditorProfileImageUrl;
	
	private String creditorQRImageUrl;

	private Integer totalRecords;

	private List<SaleRecord> saleRecords;

	private Integer totalAmount;

	private boolean recordStatus;

	private Integer totalPaidAmount;

	private Integer totalRemaininAmount;

	private Integer totalProducts;

	private List<String> productNames;

	private Integer totalTransactions;

	private List<Transaction> transactions;

}
