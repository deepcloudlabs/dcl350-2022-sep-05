package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PeriodicService {
	private final AnotherService anotherService;

	public PeriodicService(AnotherService anotherService) {
		this.anotherService = anotherService;
	}

	@Scheduled(fixedRate = 5_000)
	public void callAnotherService() {
		anotherService.run().thenAcceptAsync(System.err::println);
	}
}
