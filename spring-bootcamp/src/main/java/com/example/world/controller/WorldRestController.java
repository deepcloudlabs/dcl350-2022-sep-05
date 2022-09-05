package com.example.world.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.dao.CountryDao;
import com.example.world.domain.Country;

@RestController
@RequestScope
@CrossOrigin
@Validated
public class WorldRestController {
	// @Autowired // 1. field injection
	private CountryDao countryDao;
	
	// @Autowired // 2. setter injection
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

    @Autowired //not required since spring 4
	// constructor injection
	public WorldRestController(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

    public WorldRestController(String name) {
    	
    }

    // curl -X GET http://localhost:9200/world/api/v1/continents
	@GetMapping("/continents")
	public Collection<String> getContinents() {
		return countryDao.getAllContinents().stream().sorted().toList();
	}

	// curl -X GET http://localhost:9200/world/api/v1/countries/Asia
	@GetMapping(value = "/countries/{continent}")
	public List<Country> getCountriesByContinent(@PathVariable String continent) {
		return countryDao.findCountriesByContinent(continent);
	}
}
