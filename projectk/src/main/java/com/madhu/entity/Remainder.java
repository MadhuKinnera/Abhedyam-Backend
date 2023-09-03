package com.madhu.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Remainder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer remainderId;

	private LocalDateTime createdTimestamp;

	private LocalDate remainderDate;
	
	private String remainderMessage;
	
	private LocalDateTime remainderDateTime;
	private String description;

	@ManyToOne
	@JsonBackReference
	private SaleRecord saleRecord;

}
