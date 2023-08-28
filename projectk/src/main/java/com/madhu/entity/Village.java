package com.madhu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String villageName;
	private String mandal;
	private String district;
	private String state;
	private Integer pincode;
	
	private Integer productGoal;
	
	private Integer amountGoal;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "village")
	private List<Address> addresses = new ArrayList<>();

}
