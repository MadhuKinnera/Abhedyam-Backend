package com.madhu.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private String productName;
	private Integer buyedPrice;
	private Integer sellingPrice;
	private String description;
	private String imageUrl;
}
