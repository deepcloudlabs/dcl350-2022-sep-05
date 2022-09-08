package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String>{
	List<EmployeeDocument> findAllByBirthYearBetween(int fromYear,int toYear);
	@Query("{'yil' : {$gte : ?1, $lt: ?2 } }")
	List<EmployeeDocument> calisanlariGetirYasAraligi(int fromYear,int toYear);

}
