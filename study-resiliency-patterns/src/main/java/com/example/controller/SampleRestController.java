package com.example.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
@RequestMapping("/resources")
@RequestScope
@Validated
@CrossOrigin
public class SampleRestController {

	@Bulkhead(name = "bulkhead1", type = Type.SEMAPHORE)
	@GetMapping 
	public List<Integer> getResources(){
		return List.of(1,2,3,4,5,6);
	}
}
