package com.example.exercise;

import com.example.hr.domain.FiatCurrency;

public class EnumsAreValueObjects {

	public static void main(String[] args) {
		FiatCurrency currency = FiatCurrency.USD;
		currency = FiatCurrency.valueOf("EURO");
		System.out.println(currency);
	}

}
