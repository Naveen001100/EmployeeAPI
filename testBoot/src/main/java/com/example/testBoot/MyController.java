package com.example.testBoot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.testBoot.DTO.Employee;
import com.example.testBoot.Service.TestBootService;
import com.example.testBoot.exception.ResponseNotFoundException;

@RestController
public class MyController {
	
		@Autowired
		TestBootService testBootService;

		@Value("${app.greeting}")
		private String greet;
		
		@GetMapping("/greeting")
		public String getGreeting()
		{
			return greet;
		}

		@PostMapping("/addEmployee")
		public Employee addEmployee(@RequestBody Employee emp) {
			return testBootService.saveEmployee(emp);
		}

		@GetMapping("/getEmployee")
		public List<Employee> getEmployee()
		{
			return testBootService.getEmployee();
		}

		@GetMapping("/getEmployeeById/{id}")
		public Employee getEmployeeById(@PathVariable int id) throws ResponseNotFoundException
		{
			return testBootService.getEmployeeById(id);			
		}

		@DeleteMapping("/deleteEmployee/{id}")
		public Employee deleteEmployee(@PathVariable int id)
		{
			return testBootService.deleteEmployeeById(id);
		}
}
