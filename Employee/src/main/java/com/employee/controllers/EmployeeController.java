package com.employee.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.Employee;
import com.employee.entities.ResponseBean;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.services.EmployeeService;
import com.employee.util.ConstantValues;
import com.employee.util.HttpComponent;
import com.employee.util.SecurityHttpStatus;
import com.employee.util.URLMapping;


@RestController
@RequestMapping(path = URLMapping.ROOT_PATH)
public class EmployeeController {
	
	@Autowired
	HttpComponent headers;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping(path = URLMapping.EMPLOYEE)
	private ResponseEntity<ResponseBean> insertEmployee(@RequestBody Employee emp)
	{
		if (employeeService.insertEmployee(emp)) {

			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		} else {
			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_FAILED_TO_INSERT,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		}
	}
	
	
	@PutMapping(path = URLMapping.EMPLOYEE)
	private ResponseEntity<ResponseBean> updateEmployee(@RequestBody Employee emp)
	{
		if (employeeService.updateEmployee(emp)) {

			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB, SecurityHttpStatus.ACCEPTED,System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		} else {
			ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_FAILED_TO_INSERT,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		}

	}
	
	
	@GetMapping(path = URLMapping.EMPLOYEE_BY_ID)
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable long id) {
		Optional<Employee> emp = employeeService.getEmployeeById(id);
		if (emp.isPresent()) {
			return new ResponseEntity<Optional<Employee>>(emp, headers.getHeader(), HttpStatus.ACCEPTED);
		} else {
			throw new EmployeeNotFoundException(ConstantValues.EMPLOYEE_ID_NOT_FOUND);
		}

	}
	
	@GetMapping(path = URLMapping.EMPLOYEE)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> emp = employeeService.getAllEmployees();
		if (!emp.isEmpty()) {
			return new ResponseEntity<List<Employee>>(emp, headers.getHeader(), HttpStatus.ACCEPTED);
		} else {
			throw new EmployeeNotFoundException(ConstantValues.EMPLOYEES_NOT_FOUND);
		}

	}
	
	@DeleteMapping(path=URLMapping.EMPLOYEE_BY_ID)
	public ResponseEntity<ResponseBean> deleteEmployee(@PathVariable long id){
		Optional<Employee> emp = employeeService.getEmployeeById(id);
		if(emp.isPresent())
		{
		if (employeeService.deleteEmployee(id)) {
			ResponseBean responseBean = new ResponseBean(ConstantValues.EMPLOYEE_DELETED, SecurityHttpStatus.ACCEPTED,System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, headers.getHeader(), HttpStatus.ACCEPTED);
		} else {
			throw new EmployeeNotFoundException(ConstantValues.EMPLOYEES_NOT_FOUND);
		}
		}
		else
		{
			throw new EmployeeNotFoundException(ConstantValues.EMPLOYEES_NOT_FOUND);
		}
	}

}
