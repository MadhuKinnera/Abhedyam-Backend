package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Remainder;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;

import lombok.Data;

@Data
public class RecordResponseModel {

	private SaleRecord saleRecord;

	private Customer customer;
	private Village village;
	private Product product;

	private List<Transaction> transactions;

	private List<Remainder> remainders;

	private Integer remainingAmount;
	private Integer paidAmount;
	private Integer totalAmount;

}
