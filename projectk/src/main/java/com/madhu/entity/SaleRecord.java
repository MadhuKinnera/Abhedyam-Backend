package com.madhu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SaleRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recordId;
    private LocalDateTime timestamp;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Occasion occasion;
    private Integer totalAmount;
    private Integer quantity = 1;
    private String description;
    private Integer dueAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleRecord")
    private List<Transaction> transactions = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleRecord")
    private List<Remainder> remainders = new ArrayList<>();

}
