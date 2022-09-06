package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ddd.DomainValueObject;

@DomainValueObject
public final class BirthYear {
	private final int year;
	private static final Map<Integer, BirthYear> CACHE = new ConcurrentHashMap<>();

	private BirthYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public static BirthYear valueOf(int year) {
		if (year < 1900)
			throw new IllegalArgumentException("Birth year must be larger than 1900");
		var cachedBirthYear = CACHE.get(year);
		if (Objects.isNull(cachedBirthYear)) {
			cachedBirthYear = new BirthYear(year);
			CACHE.put(year, cachedBirthYear);
		}
		return cachedBirthYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthYear other = (BirthYear) obj;
		return year == other.year;
	}

	@Override
	public String toString() {
		return "BirthYear [year=" + year + "]";
	}

}
