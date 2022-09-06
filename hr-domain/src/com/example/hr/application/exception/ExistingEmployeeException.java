package com.example.hr.application.exception;

@SuppressWarnings("serial")
public class ExistingEmployeeException extends Exception {

	public ExistingEmployeeException(String reason) {
		super(reason);
	}

}
