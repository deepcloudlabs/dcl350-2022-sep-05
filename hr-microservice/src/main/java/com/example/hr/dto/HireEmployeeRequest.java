package com.example.hr.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
	private String identity;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Min(5500)
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Iban
	private String iban;
	@NotNull
	private Department department;
	@NotNull
	private JobStyle jobStyle;
	@NotBlank
	private String photo;
	@Min(1900)
	private int birthYear;	
}
