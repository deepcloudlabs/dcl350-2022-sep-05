package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.EmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee, EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =  context -> {
		var employee = context.getSource();
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setIdentity(employee.getIdentity().getValue());
		employeeResponse.setFirstName(employee.getFullname().getFirstName());
		employeeResponse.setLastName(employee.getFullname().getLastName());
		employeeResponse.setIban(employee.getIban().getValue());
		employeeResponse.setSalary(employee.getSalary().getValue());
		employeeResponse.setCurrency(employee.getSalary().getCurrency());
		employeeResponse.setBirthYear(employee.getBirthYear().getYear());
		employeeResponse.setDepartment(employee.getDepartment());
		employeeResponse.setJobStyle(employee.getJobStyle());
		employeeResponse.setPhoto(employee.getPhoto().getBase64EncodedValues());
		return employeeResponse;
	};
	
	private static final Converter<EmployeeEntity, Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =  context -> {
		var entity = context.getSource();
		var employee = new Employee.Builder(entity.getIdentity())
				                   .fullname(entity.getFirstName(), entity.getLastName())
				                   .iban(entity.getIban())
				                   .salary(entity.getSalary(), entity.getCurrency())
				                   .department(entity.getDepartment().name())
				                   .jobStyle(entity.getJobStyle().name())
				                   .birthYear(entity.getBirthYear())
				                   .photo(entity.getPhoto())
				                   .build();
		return employee;
	};
	
	private static final Converter<Employee, EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =  context -> {
		var employee = context.getSource();
		var entity = new EmployeeEntity();
		entity.setIdentity(employee.getIdentity().getValue());
		entity.setFirstName(employee.getFullname().getFirstName());
		entity.setLastName(employee.getFullname().getLastName());
		entity.setIban(employee.getIban().getValue());
		entity.setSalary(employee.getSalary().getValue());
		entity.setCurrency(employee.getSalary().getCurrency());
		entity.setBirthYear(employee.getBirthYear().getYear());
		entity.setDepartment(employee.getDepartment());
		entity.setJobStyle(employee.getJobStyle());
		entity.setPhoto(employee.getPhoto().getValues());
		return entity;
	};	
	
	private static final Converter<HireEmployeeRequest, Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =  context -> {
		HireEmployeeRequest request = context.getSource();
		var employee = new Employee.Builder(request.getIdentity())
				                   .fullname(request.getFirstName(), request.getLastName())
				                   .iban(request.getIban())
				                   .salary(request.getSalary(), request.getCurrency())
				                   .department(request.getDepartment().name())
				                   .jobStyle(request.getJobStyle().name())
				                   .birthYear(request.getBirthYear())
				                   .photo(request.getPhoto())
				                   .build();
		return employee;
	};
	

	@Bean
	public ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		return modelMapper;
	}
}
