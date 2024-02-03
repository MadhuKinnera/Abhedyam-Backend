package com.madhu.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;
	private LocalDateTime timestamp;
	private Integer amount;
	private String description;
	private String modeOfPayment;

	@ManyToOne
	@JsonBackReference
	private SaleRecord saleRecord;
	
	private String referenceImage;

}
