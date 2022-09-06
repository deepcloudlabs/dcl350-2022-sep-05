package com.example.hr.application.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {

	public EmployeeNotFoundException(String reason) {
		super(reason);
	}

}
