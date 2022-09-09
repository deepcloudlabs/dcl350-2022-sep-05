package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	public void doBusiness() {
		System.err.println("BusinessService is doing a business!");
		if(ThreadLocalRandom.current().nextDouble()<0.7)
			throw new IllegalArgumentException("oops!");
		System.err.println("BusinessService is returning successful!");
	}
}
