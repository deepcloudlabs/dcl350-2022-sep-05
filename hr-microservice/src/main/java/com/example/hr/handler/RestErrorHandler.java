package com.example.hr.handler;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.application.exception.EmployeeNotFoundException;
import com.example.hr.application.exception.ExistingEmployeeException;
import com.example.hr.dto.ErrorResponse;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
		return e.getConstraintViolations()
				.stream()
				.map( cv -> new ErrorResponse(cv.getMessage(),cv.getConstraintDescriptor().getAnnotation().toString()))
				.toList();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult()
				.getAllErrors()
				.stream()
				.map( err -> new ErrorResponse(err.getDefaultMessage(),err.getObjectName()))
				.toList();
	}
	

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		return new ErrorResponse(e.getMessage(), e.getCause().getClass().getName());
	}
	
	@ExceptionHandler(ExistingEmployeeException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorResponse handleExistingEmployeeException(ExistingEmployeeException e) {
		return new ErrorResponse(e.getMessage(), e.getCause().getClass().getName());
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleRuntimeException(RuntimeException e) {
		return new ErrorResponse(e.getMessage(), e.getCause().getClass().getName());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorResponse handleIllegalArgument(IllegalArgumentException e) {
		return new ErrorResponse(e.getMessage(), "");
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleException(Exception e) {
		return new ErrorResponse(e.getMessage(), "");
	}
}
