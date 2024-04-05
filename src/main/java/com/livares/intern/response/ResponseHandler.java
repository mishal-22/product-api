package com.livares.intern.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		CustomResponse customResponse = new CustomResponse(message, status.value(), responseObj);
		return new ResponseEntity<Object>(customResponse, status);
	}
}
