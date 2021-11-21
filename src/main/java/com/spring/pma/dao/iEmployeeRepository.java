package com.spring.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.pma.dto.EmployeeProject;
import com.spring.pma.entity.Employee;

public interface iEmployeeRepository extends CrudRepository<Employee, Long> {
	
	@Override
	public List<Employee> findAll();

	@Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, "
			+ "COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e "
			+ "LEFT JOIN project_employee pe ON e.employee_id = pe.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC;")
	public List<EmployeeProject> employeeProject();
	
//	@Query(nativeQuery = true, value = "UPDATE employee" + " "
//			+ "SET first_name = 'Jack'," + " "
//			+ " last_name = 'Peason'" + " "
//			+ "WHERE employee_id = 5"
//			+ ";"
//			)
//	public Boolean updateEmployee(Long id, String firstName, String lastName, String email);
}