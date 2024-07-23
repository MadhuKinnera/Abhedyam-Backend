package com.madhu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Occasion {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String occasionName;
    private LocalDate occasionDate;

    @JsonIgnore
    @OneToMany
    private List<SaleRecord> saleRecords;
}
