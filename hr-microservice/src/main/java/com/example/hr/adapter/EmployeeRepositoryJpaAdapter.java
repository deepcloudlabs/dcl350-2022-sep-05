package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository empRepo;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		Optional<EmployeeEntity> employeeEntity = empRepo.findById(identity.getValue());
		if (employeeEntity.isEmpty())
			return Optional.empty();
		return Optional.of(modelMapper.map(employeeEntity.get(), Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return empRepo.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var entity = empRepo.saveAndFlush(modelMapper.map(employee,EmployeeEntity.class));
		return modelMapper.map(entity,Employee.class);
	}

	@Override
	public Employee remove(Employee employee) {
		var identity = employee.getIdentity().getValue();
		var entity = empRepo.findById(identity);
		if (entity.isPresent()) {
			empRepo.delete(entity.get());
			return modelMapper.map(entity.get(),Employee.class);
		}
		return null;
	}

}
