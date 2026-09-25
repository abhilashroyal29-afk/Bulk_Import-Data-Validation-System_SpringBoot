package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImportRecord;
import com.example.demo.entity.ImportJob;
import java.util.List;

public interface ImportRecordRepository extends JpaRepository<ImportRecord, Long>{
	
	List<ImportRecord> findByImportJob(ImportJob importJob);
	List<ImportRecord>findByStatus(String status);
	
	long countByImportJob(ImportJob importJob);
	
	long countByStatus(String status);

}
