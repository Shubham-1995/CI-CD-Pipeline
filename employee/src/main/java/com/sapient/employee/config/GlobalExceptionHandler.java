package com.sapient.employee.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.employee.bean.ErrorMessage;
import com.sapient.employee.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private ErrorMessage errormessage;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		errormessage.setTimestamp(LocalDateTime.now());
		errormessage.setMessage(ex.getMessage());
		errormessage.setDetail(request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(errormessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		errormessage.setTimestamp(LocalDateTime.now());
		errormessage.setMessage(ex.getMessage());
		errormessage.setDetail(request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(errormessage, HttpStatus.NOT_FOUND);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		errormessage.setCount(ex.getErrorCount());	
		errormessage.setMessage(ex.getFieldError().getDefaultMessage());
		errormessage.setDetail(request.getDescription(false));
		log.info("Total Errors:" +ex.getErrorCount()  + " First Error:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errormessage, HttpStatus.BAD_REQUEST);
	}

}
