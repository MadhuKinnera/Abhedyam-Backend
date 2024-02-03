package com.madhu.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.madhu.enums.Color;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String customerName;
	private Integer age;
	private String profession;
	private String mobileNo;
	private String email;
	private String profileImageUrl;
	private String description;
	
	private String customerCode;

	@Enumerated(EnumType.STRING)
	private Color flag;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	private Address address = new Address();

	@ManyToOne
	@JsonBackReference
	private User user;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<SaleRecord> saleRecords = new ArrayList<>();

	

	@ElementCollection
	private List<String> keywords = new ArrayList<>();

}
