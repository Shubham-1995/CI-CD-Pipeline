package com.sapient.employee.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeBean {
	@JsonProperty("email_id")
	private String emailId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("role")
	private List<String> roles;
	@JsonProperty("dept")
	private String department;
	@JsonProperty("pass")
	private String password;
}
