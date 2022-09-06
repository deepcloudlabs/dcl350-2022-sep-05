package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	List<EmployeeEntity> findAllByBirthYearBetween(int fromYear,int toYear);
	// JPQL (JPA Query Language)
	@Query("select emp from EmployeeEntity e where e.birthYear between :fromyear and :toYear")
	List<EmployeeEntity> calisanlariGetirYasAraligi(int fromYear,int toYear);
	@Query(nativeQuery = true,value =  "select emp from employees e where e.birthYear between :fromyear and :toYear")
	List<EmployeeEntity> nativeCalisanlariGetirYasAraligi(int fromYear,int toYear);
}
