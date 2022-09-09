package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.Customer;

import reactor.core.publisher.Flux;

public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String>{

	@Query("{}")
	Flux<Customer> findAll(PageRequest page);

}
