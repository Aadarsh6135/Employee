package com.employee;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockitoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.entities.Employee;
import com.employee.repositories.EmployeeRepository;
import com.employee.services.EmployeeService;

@SpringBootTest
class EmployeeApplicationTests {
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;

	@Test
	public void insertEmployee() {
		Employee emp1=new Employee();
		Mockito.when(employeeRepository.save(Mockito.anyObject())).thenReturn(getEmployee());
		boolean b=employeeService.insertEmployee(getEmployee());
		System.out.println("Data is===>>>"+emp1);
		assertNotNull(emp1);
	}
	
	
	@Test
	public void insertEmployee_fail() {
		Employee emp1=new Employee();
		Mockito.when(employeeRepository.save(Mockito.anyObject())).thenReturn(getEmployee());
		boolean b=employeeService.insertEmployee(getEmployee());
		System.out.println("Data is===>>>"+emp1);
		assertThatNullPointerException();
	}
	
	@Test
	public void getAllEmployee() {
		List<Employee> emp1=new ArrayList<> ();
		Mockito.when(employeeRepository.findAll()).thenReturn(getAll());
		List<Employee> b=employeeService.getAllEmployees();
		System.out.println("Data is===>>>"+emp1);
		assertThatNullPointerException();
	}
	
	@Test
	public void updateEmployee() {
		Employee emp1=new Employee();
		Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(getUpdateEmployee());
		boolean b=employeeService.updateEmployee(getUpdateEmployee1());
		System.out.println("Data is===>>>"+b);
		assertTrue(b);
	}
	
	private List<Employee> getAll()
	{
		List<Employee> emp=new CopyOnWriteArrayList<>();
		Employee emp1=new Employee("Aadarsh","ch","aadarsh.chowdary@gmail.com",24L,"M","LB Nagar");
		emp.add(emp1);
		return emp;
	}
	
	private Employee getUpdateEmployee1()
	{
		Employee emp=new Employee();
		emp.setId(1L);
		emp.setFirstName("Aadarsh");
		emp.setLastName("ch");
		emp.setEmailId("aadarsh.chowdary@gmail.com");
		emp.setGender("M");
		emp.setAddress("LB Nagar");
		emp.setAge(24L);
		return emp;
	}
	
	private Optional<Employee> getUpdateEmployee()
	{
		Optional<Employee> empOptional = Optional.of(getUpdateEmployee1());
		return empOptional;
	}
	
	private Employee getEmployee()
	{
		Employee emp=new Employee("Aadarsh","ch","aadarsh.chowdary@gmail.com",24L,"M","LB Nagar");
		return emp;
	}
	
	

}
