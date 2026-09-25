package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="import_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "job_id")
	private ImportJob importJob;
	
	@NotBlank(message="Record data cannot be empty")
	@Lob
	@Column(nullable=false)
	private String data;
	
	@NotBlank(message = "Status Is Required")
	@Column(nullable=false)
	private String status;
	@Size(max=500,message = "Error message should not exceeded 500 characters")
	@Column(name="error_message")
	private String errorMessage;
	
	

}
