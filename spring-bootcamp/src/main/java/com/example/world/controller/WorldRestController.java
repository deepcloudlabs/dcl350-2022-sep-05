package com.example.world.controller;

import java.util.Collection;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@CrossOrigin
@Validated
public class WorldRestController {

	@GetMapping("/continents")
	public Collection<String> getContinents(){
		
	}
	@GetMapping("/countries/{continent}")
	public Collection<String> getCountriesByContinent(@PathVariable String continent){
		
	}
}
