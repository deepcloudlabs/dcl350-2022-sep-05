package com.example.hr.repository;

import java.util.Optional;

import com.example.hexagonal.DrivenSide;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@DrivenSide
public interface EmployeeRepository {

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	boolean existsByIdentity(TcKimlikNo identity);

	Employee persist(Employee employee);

	Employee remove(Employee foundEmployee);

}
