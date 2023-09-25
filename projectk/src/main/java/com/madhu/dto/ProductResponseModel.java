package com.madhu.dto;

import java.util.List;

import com.madhu.entity.Product;

import lombok.Data;


@Data
public class ProductResponseModel {
	
	private Product product;
	
	private Integer productSellCount;
	
	private Integer totalSelledAmount;
	
	private Integer collectedAmount;
	
	private Integer pendingAmount;
	
	private List<VillageResponseDTO> villageWiseCount;
	 
	
	

}
