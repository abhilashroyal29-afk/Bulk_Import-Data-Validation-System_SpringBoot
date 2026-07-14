package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportRequestDto {
	
	@NotBlank(message="File name is required")
	private String fileName;
	
	@NotBlank(message = "File type is required")
	private String fileType;

}
