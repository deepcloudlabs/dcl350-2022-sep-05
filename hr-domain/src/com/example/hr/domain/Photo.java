package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

import com.example.ddd.DomainValueObject;

@DomainValueObject
public final class Photo {
	private final byte[] values;
	private final int hashCode;
	
	private Photo(byte[] values) {
		this.values = values;
		this.hashCode = Objects.hash(values);
	}

	public byte[] getValues() {
		return values;
	}

	public String getBase64EncodedValues() {
		return Base64.getEncoder().encodeToString(values);
	}

	public static Photo of(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}

	public static Photo of(String base64EncodedValues) {
		Objects.requireNonNull(base64EncodedValues);
		var values = Base64.getDecoder().decode(base64EncodedValues);
		return new Photo(values);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hashCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		return hashCode == other.hashCode;
	}

	@Override
	public String toString() {
		return "Photo [hashCode=" + hashCode + "]";
	}
	
	
}
