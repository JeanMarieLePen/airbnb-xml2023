package com.xml.mainapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<String> handleUnssuportedMediaTypeException(HttpMediaTypeNotSupportedException exception){
		System.out.println("ERROR HANDLER");
		System.out.println(exception.getLocalizedMessage());
		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(String.format("Unsupported media type: %s", exception.getRootCause().getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
