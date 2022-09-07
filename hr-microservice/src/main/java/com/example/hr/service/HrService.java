package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.exception.EmployeeNotFoundException;
import com.example.hr.application.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.EmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;

	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findEmployeeById(String identity) {
		var foundEmployee = hrApplication.getEmployee(TcKimlikNo.valueOf(identity));
		var employee = foundEmployee.orElseThrow(() -> new IllegalArgumentException("Cannot find employee"));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Transactional()
	public EmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request,Employee.class);
		try {
			employee = hrApplication.hireEmployee(employee);
		} catch (ExistingEmployeeException e) {
		    throw new IllegalArgumentException(e.getMessage()); 
		}
		return modelMapper.map(employee,EmployeeResponse.class);
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		try {
			var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity));
			return modelMapper.map(employee,EmployeeResponse.class);
		} catch (EmployeeNotFoundException e) {
		    throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
