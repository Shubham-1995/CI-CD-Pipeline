package com.sapient.employee.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "name")
	private String name;
	@Column(name = "role")
	private Set<Role> roles;
	@Column(name = "dept")
	private String department;
	@Column(name="pass")
	private String password;
}
