package com.example.hr.domain;

import com.example.ddd.DomainEntity;

// modeling, abstraction
// bounded-context -> sub-domain
// ubiquitous language: Employee, TcKimlikNo, FullName, Money, Iban,...
// DDD: Entity -> Aggregate Root, Value Object (VO)
// Entity -> i) persistent, ii) identity iii) business methods -> mutable
@DomainEntity(identity={"identity"})
public class Employee {
	private TcKimlikNo identity;
	private FullName fullname;
	private Money salary;
	private Iban iban;
	private Department department;
	private JobStyle jobStyle;
	private Photo photo;
	private BirthYear birthYear;
}
