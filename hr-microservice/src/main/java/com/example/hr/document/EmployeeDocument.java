package com.example.hr.document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Data;

@Document(collection="employees")
@Data
public class EmployeeDocument {
	@Id
	private String identity;
	@Field("fname")
	@NotBlank
	private String firstName;
	@Field("lname")
	@NotBlank
	private String lastName;
	@Field("maas")
	@Min(5500)
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@NotBlank
	@Indexed(unique = true)
	private String iban;
	@NotNull
	private Department department;
	@NotNull
	private JobStyle jobStyle;
	@NotNull
	private String photo;
	@Field(name="yil")
	@Min(1900)
	@Indexed
	private int birthYear;
}
