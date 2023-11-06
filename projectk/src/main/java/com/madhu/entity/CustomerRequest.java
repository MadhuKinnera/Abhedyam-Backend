package com.madhu.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
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
	@ElementCollection
	private List<String> referenceImages;

	@ManyToOne
	private User user;
}
