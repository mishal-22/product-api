package com.livares.intern.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	/**
	 * Returns custom error response on occurrence of custom exception.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ErrorResponse> handleMyCustomException(CustomException e) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(e.getMessage());
		error.setErrorCode(e.getErrorCode());
		error.setSuccess(false);
		return new ResponseEntity<>(error, e.getHttpStatus());
	}
}
