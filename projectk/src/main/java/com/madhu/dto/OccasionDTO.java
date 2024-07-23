package com.madhu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OccasionDTO {

    private String occasionName;
    private LocalDate occasionDate;
}
