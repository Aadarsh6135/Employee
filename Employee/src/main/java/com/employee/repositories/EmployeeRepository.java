package com.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value="select a.id, a.firstname, a.lastname,a.emailid,a.age,a.gender,a.address from employee a where a.id =:id",nativeQuery = true)
	public Employee deleteEmployee(@Param("id") Long id);
}
