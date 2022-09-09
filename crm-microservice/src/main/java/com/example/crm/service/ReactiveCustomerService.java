package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.repository.CustomerReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {

	private final CustomerReactiveRepository customerRepo;
	
	public ReactiveCustomerService(CustomerReactiveRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	public Mono<Customer> acquireCustomer(Customer customer) {
		return customerRepo.insert(customer);
	}

	public Mono<Customer> releaseCustomer(String customerId) {
		return customerRepo.findById(customerId)
				           .doOnSuccess(customer -> customerRepo.delete(customer)
				        		                                .subscribe((u) -> System.err.println("Customer is released!"))
				        		   );           
	}

	public Mono<Customer> findCustomerById(String customerId) {
		return customerRepo.findById(customerId);
	}

	public Flux<Customer> findCustomers(int pageno, int pagesize) {
		return customerRepo.findAll(PageRequest.of(pageno, pagesize));
	}

}
