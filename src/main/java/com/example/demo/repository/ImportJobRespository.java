package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImportJob;



public interface ImportJobRespository extends JpaRepository<ImportJob, Long>{
	
	Optional<ImportJob> findByFileName(String fileName);
	
	boolean existsByFileName(String fileName);
	

}
