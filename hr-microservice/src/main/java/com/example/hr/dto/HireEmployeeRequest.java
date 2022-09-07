package com.example.hr.dto;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	private String identity;
	private String firstName;
	private String lastName;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private Department department;
	private JobStyle jobStyle;
	private String photo;
	private int birthYear;	
}
