package com.madhu.dto;

import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import lombok.Data;

import java.util.List;

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

    private Integer totalRemainingAmount;

    private String description;

}
