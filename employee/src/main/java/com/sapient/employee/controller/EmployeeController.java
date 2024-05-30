package com.sapient.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.employee.bean.EmployeeBean;
import com.sapient.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/create")
	public void createEmployee(@RequestBody EmployeeBean employeeBean) {
		employeeService.create(employeeBean);
	}
	
	@PutMapping("/update/{email_id}")
	public void updateEmployee(@RequestBody EmployeeBean employeeBean,@PathVariable(name = "email_id") String emailId) {
		employeeService.update(emailId,employeeBean);
	}

	@GetMapping("/all")
	public List<EmployeeBean> getAllEmployee() {
		return employeeService.getAll();
	}
	
	@GetMapping("/{email_id}")
	public EmployeeBean getEmployeeById(@PathVariable(name = "email_id") String emailId) {
		return employeeService.getByEmailId(emailId);
	}

}