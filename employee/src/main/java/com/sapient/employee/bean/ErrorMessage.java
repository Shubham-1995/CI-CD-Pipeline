package com.sapient.employee.bean;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class ErrorMessage {
	private LocalDateTime timestamp;
	private String message;
	private HttpStatusCode errcode; 
	private String detail;
	private Integer count;
}
