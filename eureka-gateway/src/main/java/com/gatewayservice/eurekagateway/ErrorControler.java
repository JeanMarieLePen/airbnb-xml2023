package com.gatewayservice.eurekagateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	
	@ExceptionHandler(UnsatisfiedDependencyException.class)
	public ResponseEntity<String> handleUnssuportedMediaTypeException(HttpMediaTypeNotSupportedException exception){
		System.out.println("ERROR HANDLER");
		System.out.println(exception.getLocalizedMessage());
		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(String.format("PROBLEM: ", exception.getRootCause().getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
