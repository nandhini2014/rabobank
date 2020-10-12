package com.rabobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Class CustomerStatementExceptionController.
 */
@ControllerAdvice
public class CustomerStatementExceptionController {

	/**
	 * Exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(FileTypeInvalidException.class)
	public ResponseEntity<Object> exception(FileTypeInvalidException ex) {
		return new ResponseEntity<>("File Type Not supported", HttpStatus.BAD_REQUEST);

	}

}
