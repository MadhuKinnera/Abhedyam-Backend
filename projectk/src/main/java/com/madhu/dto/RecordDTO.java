package com.madhu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDTO {

    private LocalDate endDate;
    private Integer occasionId;
    private Integer totalAmount;
    private Integer quantity = 1;
    private String description;

    private Integer customerId;

    private Integer productId;


}
