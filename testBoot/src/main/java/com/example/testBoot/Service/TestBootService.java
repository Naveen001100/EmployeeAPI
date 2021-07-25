package com.example.testBoot.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.example.testBoot.DTO.Employee;
import com.example.testBoot.Entity.EmployeeEntity;
import com.example.testBoot.Repository.TestBootRepository;
import com.example.testBoot.exception.ResponseNotFoundException;

@Service
@EnableCaching
public class TestBootService {

	@Autowired
	TestBootRepository testBootRepository;
	
	public Employee saveEmployee(Employee emp) {
		EmployeeEntity empEnt = convertDTOtoENT(emp);
		return ConvertENTtoDTO(testBootRepository.save(empEnt));
		
	}

	private Employee ConvertENTtoDTO(EmployeeEntity empEnt) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setId(empEnt.getId());
		emp.setName(empEnt.getName());
		emp.setDept(empEnt.getDept());
		return emp;
	}

	private EmployeeEntity convertDTOtoENT(Employee emp) {
		EmployeeEntity empEnt = new EmployeeEntity();
		empEnt.setName(emp.getName());
		empEnt.setDept(emp.getDept());
		return empEnt;
	}

	public List<Employee> getEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeEntity> empEntList = testBootRepository.findAll();
		List<Employee> empList = new LinkedList<>();
		for(EmployeeEntity emp:empEntList) {
			empList.add(ConvertENTtoDTO(emp));
		}
		return empList;
	}
	
	@Cacheable(key="#id",value="employee")
	public Employee getEmployeeById(int id) throws ResponseNotFoundException {
		// TODO Auto-generated method stub
		EmployeeEntity empEnt;
			empEnt = testBootRepository.findById(id)
						.orElseThrow(()->new ResponseNotFoundException("Something went wrong"));
		System.out.println("This is the data I am getting from DB");						
		return ConvertENTtoDTO(empEnt);
	}

	@CacheEvict(key="#id",value="employee")
	public Employee deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		testBootRepository.deleteById(id);;
		return null;
	}
}
