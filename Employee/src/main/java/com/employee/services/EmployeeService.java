package com.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entities.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.repositories.EmployeeRepository;
import com.employee.util.ConstantValues;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	Optional<Employee> emp;
	
	//Insert
	public boolean insertEmployee(Employee emp)
	{
		Employee employee = new Employee();
		if (emp.getFirstName().isBlank() || emp.getLastName().isBlank() || emp.getEmailId().isBlank()
				|| emp.getAge() == null || emp.getGender().isBlank() || emp.getAddress().isBlank()) {
			return false;
		} else {
			employee = employeeRepository.save(new Employee(emp.getFirstName(), emp.getLastName(), emp.getEmailId(),
					emp.getAge(), emp.getGender(), emp.getAddress()));
			return true;
		}

	}
	
	//Update
	public boolean updateEmployee(Employee emp)
	{
		Employee empsave = new Employee();
		if (emp.getFirstName().isBlank() || emp.getId() == null && emp.getLastName().isBlank()
				&& emp.getEmailId().isBlank() && emp.getAge() == null && emp.getGender().isBlank()
				&& emp.getAddress().isBlank()) {
			return false;
		} else {
			Optional<Employee> employee1 = employeeRepository.findById(emp.getId());
			if (employee1.isPresent()) {
				employee1.get().setAddress(emp.getAddress());
				employee1.get().setAge(emp.getAge());
				employee1.get().setFirstName(emp.getFirstName());
				employee1.get().setLastName(emp.getLastName());
				employee1.get().setGender(emp.getGender());
				employee1.get().setEmailId(emp.getEmailId());
				empsave = employeeRepository.save(employee1.get());
				return true;
			} else {
				return false;
			}
		}
	}
	
	//GetById
	public Optional<Employee> getEmployeeById(Long id)
	{
		if (id != null) {
			emp = employeeRepository.findById(id);
			return emp;
		} else {
			throw new EmployeeNotFoundException(ConstantValues.EMPLOYEE_ID_NOT_FOUND);
		}
	}
	
	//GetAllEmployees
	public List<Employee> getAllEmployees()
	{
		List<Employee> emps=employeeRepository.findAll();
		return emps;
	}

	//DeleteById
	public boolean deleteEmployee(Long id)
	{
			Employee emp = employeeRepository.deleteEmployee(id);
			employeeRepository.delete(emp);
			return true;
		} 
	










}
