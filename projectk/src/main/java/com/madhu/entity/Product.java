package com.madhu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	@Column(unique = true)
	private String productName;
	private Integer buyedPrice;
	private Integer sellingPrice;
	private String imageUrl;
	private String description;

	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
	private List<SaleRecord> saleRecords = new ArrayList<>();


}
