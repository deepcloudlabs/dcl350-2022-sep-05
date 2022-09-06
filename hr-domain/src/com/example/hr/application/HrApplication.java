package com.example.hr.application;

import java.util.Optional;

import com.example.hexagonal.DrivingSide;
import com.example.hr.application.exception.EmployeeNotFoundException;
import com.example.hr.application.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@DrivingSide
public interface HrApplication {
	Employee hireEmployee(Employee employee) throws ExistingEmployeeException;

	Employee fireEmployee(TcKimlikNo identity) throws EmployeeNotFoundException;

	Optional<Employee> getEmployee(TcKimlikNo identity);
}
