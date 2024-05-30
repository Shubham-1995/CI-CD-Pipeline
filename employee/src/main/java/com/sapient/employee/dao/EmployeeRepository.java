package com.sapient.employee.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee> findByEmailId(String emailId);
}
