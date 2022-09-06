package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.EmployeeResponse;

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
	
	@Bean
	public ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		return modelMapper;
	}
}
