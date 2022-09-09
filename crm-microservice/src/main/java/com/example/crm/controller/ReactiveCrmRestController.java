package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.Customer;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@Validated
@CrossOrigin
public class ReactiveCrmRestController {

	private final ReactiveCustomerService customerService;

	public ReactiveCrmRestController(ReactiveCustomerService customerService) {
		this.customerService = customerService;
	}

	// Reactive Stream: Flux<> -> Mono<>, Filter/Map/Reduce -> Async.
	@GetMapping("{customerId}")
	public Mono<Customer> getCustomerById(@PathVariable String customerId) {
		return customerService.findCustomerById(customerId);
	}

	@GetMapping(params = { "pageno", "pagesize" })
	public Flux<Customer> getCustomers(@RequestParam int pageno, @RequestParam int pagesize) {
		return customerService.findCustomers(pageno, pagesize);
	}

	@PostMapping
	public Mono<Customer> acquireCustomer(@RequestBody Customer customer) {
		return customerService.acquireCustomer(customer);
	}

	@DeleteMapping("{customerId}")
	public Mono<Customer> releaseCustomer(@PathVariable String customerId) {
		return customerService.releaseCustomer(customerId);
	}
}
