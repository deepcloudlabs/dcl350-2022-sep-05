package com.example.exercise;

import java.util.List;

public class StudyImmutability {

	public static void main(String[] args) {
		int z = 42; // 4-byte
		Integer x = Integer.valueOf(42); 
		Integer y = 42; 
		Integer u = 542; // 12-Byte (Header) + 4-Byte (int value) = 16-byte
		Integer v = 542;
		System.out.println("x==y ? "+(x==y));
		System.out.println("u==v ? "+(u==v));
		List<Integer> numbers;
	}

}
