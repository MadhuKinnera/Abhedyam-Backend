package com.madhu.dto;

import lombok.Data;

@Data
public class TransactionDTO {

	private Integer amount;

	private String description;

	private Integer recordId;

}
