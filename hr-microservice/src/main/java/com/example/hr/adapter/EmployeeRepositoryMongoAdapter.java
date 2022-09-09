package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name="persistenceTarget", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository empRepo;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		Optional<EmployeeDocument> employeeDocument = empRepo.findById(identity.getValue());
		if (employeeDocument.isEmpty())
			return Optional.empty();
		return Optional.of(modelMapper.map(employeeDocument.get(), Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return empRepo.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var entity = empRepo.insert(modelMapper.map(employee,EmployeeDocument.class));
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
