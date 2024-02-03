package com.madhu.dto;

import java.time.LocalDate;

import com.madhu.enums.OccasionType;

import lombok.Data;

@Data
public class RecordDTO {

	private LocalDate endDate;
	private OccasionType occasion;
	private Integer totalAmount;
	private Integer quantity = 1;
	private String description;
	
	private Integer customerId;
	
	private Integer productId;
	

}
