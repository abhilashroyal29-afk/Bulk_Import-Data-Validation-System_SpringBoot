package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  ApiResponseDto {
	
	private  String message;
	
	
	private  boolean success;
	
	private  Object data;

}
