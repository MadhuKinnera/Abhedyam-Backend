package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Village;

import lombok.Data;

@Data
public class VillageResponseModel {

	private Village village;

	private Integer totalCustomersCount;
	
	private Integer totalActiveCustomers;

	private List<Customer> activeCustomers;
	

	private Integer totalRecordsCount;
	private Integer completedRecords;

	private Integer totalActiveRecords;
	
	private List<SaleRecord> activeRecords; 

	
	private Integer totalAmountFromVillage;
	
	private Integer pendingAmount;

	private Integer collectedAmount;
	
	private Integer totalProductSellCount;

	private String goalStatus;

}
