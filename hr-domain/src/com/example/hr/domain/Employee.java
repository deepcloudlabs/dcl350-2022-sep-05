package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.DomainEntity;

// modeling, abstraction
// bounded-context -> sub-domain
// ubiquitous language: Employee, TcKimlikNo, FullName, Money, Iban,...
// DDD: Entity -> Aggregate Root, Value Object (VO)
// Entity -> i) persistent, ii) identity iii) business methods -> mutable
@DomainEntity(identity = { "identity" })
public class Employee {
	private TcKimlikNo identity;
	private FullName fullname;
	private Money salary;
	private Iban iban;
	private Department department;
	private JobStyle jobStyle;
	private Photo photo;
	private BirthYear birthYear;
	
	public Money increaseSalary(double rate) {
		this.salary = this.salary.multiply(1.0 + rate/100.);
		return this.salary;
	}
	
	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.department = builder.department;
		this.jobStyle = builder.jobStyle;
		this.photo = builder.photo;
		this.birthYear = builder.birthYear;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban=" + iban
				+ ", department=" + department + ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}

	public static class Builder {
		private TcKimlikNo identity;
		private FullName fullname;
		private Money salary;
		private Iban iban;
		private Department department;
		private JobStyle jobStyle;
		private Photo photo;
		private BirthYear birthYear;

		public Builder(String identity) {
			this.identity = TcKimlikNo.valueOf(identity);
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.of(firstName, lastName);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder photo(String base64EncodedValues) {
			this.photo = Photo.of(base64EncodedValues);
			return this;
		}

		public Employee build() {
			// Business Rule
			// Invariants
			// Constraint
			// Validation
			return new Employee(this);
		}
	}
}
