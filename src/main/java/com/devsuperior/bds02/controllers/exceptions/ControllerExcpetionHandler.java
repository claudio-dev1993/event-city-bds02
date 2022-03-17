package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.exceptions.DatabaseException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExcpetionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<EntityNotFoundError> entityNotFound(ResourceNotFoundException e, HttpServletRequest req){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		EntityNotFoundError err = new EntityNotFoundError(
				Instant.now()
				, status.value()
				,"Resource not found!" 
				, e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<BadRequestError> entityNotFound(DatabaseException e, HttpServletRequest req){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		BadRequestError err = new BadRequestError(
				Instant.now()
				, status.value()
				,"Bad request for independent Id!" 
				, e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
