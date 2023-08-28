package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Product;

import lombok.Data;


@Data
public class ProductResponseModel {
	
	private Product product;
	
	private Integer productSellCount;
	
	private Integer pendingAmount;
	
	private Integer collectedAmount;
	
	private Integer totalSelledAmount;
	
	private List<VillageResponseDTO> villageWiseCount;
	 
	
	

}
