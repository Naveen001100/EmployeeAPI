package com.example.testBoot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.testBoot.DTO.Employee;
import com.example.testBoot.Service.TestBootService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestBootApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private TestBootService testBootService;
	
	Employee mockEmp = new Employee(1,"Naveen","IT");
	
	@Test
	void contextLoads() throws Exception {
		Mockito.when(
				testBootService.getEmployeeById(Mockito.anyInt()))
								.thenReturn(mockEmp);
		Employee result = restTemplate.
				getForObject("http://localhost:"+port+"/getEmployeeById/1"
														, Employee.class);
		System.out.println(result);
		String expected = "{id:1,name:Naveen,dept:IT}";
		JSONAssert.assertEquals(expected, result.toString(), false);
	}

}
