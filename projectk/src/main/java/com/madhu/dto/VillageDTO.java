package com.madhu.dto;

import lombok.Data;

@Data
public class VillageDTO {

	private String villageName;
	private String mandal;
	private String district;
	private String state;
	private Integer pincode;

	private Integer productGoal;

	private Integer amountGoal;

}
