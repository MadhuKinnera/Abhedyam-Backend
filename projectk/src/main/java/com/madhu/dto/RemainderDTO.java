package com.madhu.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RemainderDTO {
	

	private LocalDateTime remainderDateTime;
	private String remainderMessage;
    private Integer recordId;
    private String description;

}
