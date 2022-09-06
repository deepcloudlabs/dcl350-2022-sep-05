package com.example.hr.domain;

import com.example.ddd.DomainEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@DomainEntity(identity = { "identity" })
@Builder
@Getter
@Setter
@EqualsAndHashCode(of="identity")
@ToString(exclude = "photo")
public class EmployeeWithLombok {
	private TcKimlikNo identity;
	private FullName fullname;
	private Money salary;
	private Iban iban;
	private Department department;
	private JobStyle jobStyle;
	private Photo photo;
	private BirthYear birthYear;
	private String email;
	
	public Money increaseSalary(double rate) {
		this.salary = this.salary.multiply(1.0 + rate/100.);
		return this.salary;
	}
	
}
