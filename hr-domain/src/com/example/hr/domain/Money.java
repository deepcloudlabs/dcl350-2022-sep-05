package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ddd.DomainValueObject;

@DomainValueObject
public final class Money {
	private static final Map<Integer, Money> CACHE = new ConcurrentHashMap<>();

	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money valueOf(double value, FiatCurrency currency) { // factory method
		// validation
		if (value < 0.0)
			throw new IllegalArgumentException("Money value must be positive.");
		Objects.requireNonNull(currency);
		// object pooling (caching) -> immutable class
		var key = Objects.hash(value, currency);
		var cachedMoney = CACHE.get(key);
		if (Objects.isNull(cachedMoney)) {
			cachedMoney = new Money(value, currency);
			CACHE.put(key, cachedMoney);
		}
		return cachedMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}

	public Money multiply(double rate) {
		return Money.valueOf(value * rate, currency);
	}

}
