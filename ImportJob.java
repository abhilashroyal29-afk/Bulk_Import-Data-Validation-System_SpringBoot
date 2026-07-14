package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="import_job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportJob {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="file name is required")
	@Column(name= "file_name",nullable=false)
	private String fileName;
	
	@NotBlank(message = "Status is required")
	@Column(nullable=false)
	private String status;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	public void prePersist() {
		this.createdAt=LocalDateTime.now();
	}

}
