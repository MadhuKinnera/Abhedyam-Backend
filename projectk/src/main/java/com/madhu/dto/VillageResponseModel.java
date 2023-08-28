package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Village;

import lombok.Data;

@Data
public class VillageResponseModel {

	private Village village;

	private Integer totalActiveCustomers;

	private Integer totalCustomersCount;

	private List<Customer> activeCustomers;

	private List<SaleRecord> activeRecords;

	private Integer pendingRecords;

	private Integer completedRecords;

	private Integer totalRecordsCount;

	private Integer pendingAmount;

	private Integer collectedAmount;

	private Integer totalAmountFromVillage;

	private Integer totalProductSellCount;

	private String goalStatus;

}
