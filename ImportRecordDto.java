package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportRecordDto {
	
	@NotBlank(message = "Data is required")
	private String data;
	
	private String status;
	
	private String errorMessage;

}
