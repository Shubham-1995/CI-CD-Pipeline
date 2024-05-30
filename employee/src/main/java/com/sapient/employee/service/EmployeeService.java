package com.sapient.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.employee.bean.EmployeeBean;
import com.sapient.employee.dao.EmployeeRepository;
import com.sapient.employee.entity.Employee;
import com.sapient.employee.exception.UserNotFoundException;
import com.sapient.employee.util.PasswordUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ServiceUtil serviceUtil;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordUtility passwordUtils;
	

	@Transactional
	public void create(EmployeeBean employeeBean) {
		log.info("check employee bean get data or not -----------------> {} ", employeeBean.toString());
		Employee employee = mapper.map(employeeBean, Employee.class);
		employee.setPassword(passwordUtils.generateSecurePassword(employee.getPassword()));
		log.info("check employee entity get data or not -----------------> {} ", employee.toString());
		employee.setRoles(serviceUtil.getRolesAsEnum(employeeBean.getRoles()));
		empRepo.saveAndFlush(employee);
	}

	@Transactional
	public void update(String emailId, EmployeeBean employeeBean) {
		log.info("check employee bean get data or not -----------------> {} ", employeeBean.toString());
		Optional<Employee> employeeOption = empRepo.findByEmailId(emailId);
		if (employeeOption.isPresent()) {
			Employee employee = employeeOption.get();
			mapper.map(employeeBean, employee);
			log.info("check employee entity get data or not -----------------> {} ", employee.toString());
			employee.setRoles(serviceUtil.getRolesAsEnum(employeeBean.getRoles()));
			empRepo.saveAndFlush(employee);
			return;
		}
		throw new UserNotFoundException(emailId);
	}

	public List<EmployeeBean> getAll() {
		List<Employee> employees = empRepo.findAll();
		return employees.stream().map(employee -> {
			EmployeeBean empBean = mapper.map(employee, EmployeeBean.class);
			empBean.setRoles(serviceUtil.getRolesAsString(employee.getRoles()));
			return empBean;
			}).collect(Collectors.toList());
	}

	public EmployeeBean getByEmailId(String emailId) {
		Optional<Employee> employeeOption = empRepo.findByEmailId(emailId);
		if (employeeOption.isPresent()) {
			Employee employee = employeeOption.get();
			EmployeeBean empBean = mapper.map(employee, EmployeeBean.class);
			empBean.setRoles(serviceUtil.getRolesAsString(employee.getRoles()));
			return empBean;
		}
		throw new UserNotFoundException(emailId);
	}

}
