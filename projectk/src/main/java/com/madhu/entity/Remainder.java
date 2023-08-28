package com.madhu.entity;

import java.time.LocalDateTime;

import com.madhu.enums.OccasionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	private LocalDateTime defaultDate;

	@Enumerated(EnumType.STRING)
	private OccasionType occasion;
	private String description;

	@ManyToOne
	private SaleRecord saleRecord;

}
