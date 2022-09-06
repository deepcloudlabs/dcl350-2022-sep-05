package com.example.hr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.EmployeeResponse;
import com.example.hr.dto.FireEmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.dto.HireEmployeeResponse;
import com.example.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
public class HrRestController { //Adapter: http protocol <--> Java Class
	private HrService hrService;

	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("/{identity}")
	public ResponseEntity<EmployeeResponse> getEmployeeByIdentity(@PathVariable String identity) {
		return ResponseEntity.ok(hrService.findEmployeeById(identity));
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("/{identity}")
	public FireEmployeeResponse hireEmployee(@PathVariable String identity) {
		return hrService.fireEmployee(identity);
	}

}
