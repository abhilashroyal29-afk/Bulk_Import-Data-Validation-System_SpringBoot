package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportSummaryDto {
	
	private Long jobId;
	
	private int totalRecords;
	
	private int successCount;
	
	private int failedCount;
	
	private String status;




	

}
