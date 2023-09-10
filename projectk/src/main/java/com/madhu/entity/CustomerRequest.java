package com.madhu.entity;

import java.time.LocalDateTime;

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
public class CustomerRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer crId;

	private LocalDateTime timestamp;

	private String message;

	private String referenceImages;

	@ManyToOne
	private Customer customer;
}
