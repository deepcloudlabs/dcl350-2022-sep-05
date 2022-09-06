package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.exception.EmployeeNotFoundException;
import com.example.hr.application.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeFiredEvent;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	private EventPublisher<HrEvent> eventPublisher;

	// constructor injection
	public StandardHrApplication(
			  EmployeeRepository employeeRepository, 
			  EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) throws ExistingEmployeeException {
		var identity = employee.getIdentity();
		if (employeeRepository.existsByIdentity(identity))
			throw new ExistingEmployeeException("Employee already exists.");
		var hiredEmployee = employeeRepository.persist(employee);
		eventPublisher.publish(new EmployeeHiredEvent(identity));
		return hiredEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo identity) throws EmployeeNotFoundException {
		var employee = employeeRepository.findEmployeeByIdentity(identity);
		var foundEmployee = employee.orElseThrow(() -> new EmployeeNotFoundException("Employee does not exist."));
		var firedEmployee = employeeRepository.remove(foundEmployee);
		eventPublisher.publish(new EmployeeFiredEvent(identity));
		return firedEmployee;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

}
