package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@SuppressWarnings("unused")
@Service
public class AnotherService {
	private final BusinessService businessService;

	public AnotherService(BusinessService businessService) {
		this.businessService = businessService;
	}

	//@Retry(name = "retry1", fallbackMethod = "gun")
	public void fun() {
		System.err.println("AnotherService have fun!");
		businessService.doBusiness();
	}

	//@RateLimiter(name="ratelimiter1")
	public void sun() {
		System.err.println("AnotherService have sun!");
	}

	@CircuitBreaker(name = "cb1")
	public void tun() {
		System.err.println("AnotherService have tun!");
		businessService.doBusiness();
	}
	
	@TimeLimiter(name = "timelimiter1", fallbackMethod = "runFallback")
	public CompletableFuture<Integer> run() {
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("AnotherService have run!");
			if (ThreadLocalRandom.current().nextBoolean()) {
				System.err.println("AnotherService have run sleeps 3 seconds!");			
				try {TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
				return 42;
			}
			return 108;
		});
	}

	public void gun(Throwable t) {
		System.err.println("Fallback method, gun(), is running...[%s]".formatted(t.getMessage()));
	}
	
	public CompletableFuture<Integer> runFallback(Throwable t) {
		return CompletableFuture.supplyAsync(() -> { 
			System.err.println("Fallback method, runFallback(), is running...[%s]".formatted(t.getMessage()));
			return 549;			
		});
	}
}
