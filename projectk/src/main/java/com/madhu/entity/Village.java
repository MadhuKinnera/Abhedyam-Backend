package com.madhu.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Village {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer villageId;
	@Column(unique = true)
	private String villageName;
	private String imageUrl;
	private String mandal;
	private String district;
	private String state;
	private Integer pincode;
	
	private Integer productGoal;
	
	private Integer amountGoal;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "village")
	private List<Address> addresses = new ArrayList<>();

	@ManyToOne
	@JsonBackReference
	private User user;
}
