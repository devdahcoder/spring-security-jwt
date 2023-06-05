package com.devdahcoder.exception.util;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.exception.api.model.ApiExceptionResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> getCreateValidationHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

		Map<String, String> errors = new HashMap<>();

		methodArgumentNotValidException
				.getBindingResult()
				.getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ApiNotFoundException.class)
	public ResponseEntity<Object> getNotFoundExceptionHandler(ApiNotFoundException apiNotFoundException) {

		ApiExceptionResponseModel userExceptionResponse = new ApiExceptionResponseModel(
				apiNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND,
				HttpStatus.NOT_FOUND.value(),
				ZonedDateTime.now(ZoneId.of("Z")
				)
		);

		return new ResponseEntity<>(userExceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ApiAlreadyExistException.class)
	public ResponseEntity<Object> getAlreadyExistHandler(ApiAlreadyExistException apiAlreadyExistException) {

		ApiExceptionResponseModel userExceptionResponse = new ApiExceptionResponseModel(
				apiAlreadyExistException.getMessage(),
				HttpStatus.CONFLICT,
				HttpStatus.CONFLICT.value(),
				ZonedDateTime.now(ZoneId.of("Z"))
				);

		return new ResponseEntity<>(userExceptionResponse, HttpStatus.CONFLICT);

	}

//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(UserException.class)
//	public ResponseEntity<Object> userServerExceptionHandler(UserException userException) {
//
//		UserExceptionResponse userExceptionResponse = new UserExceptionResponse(userException.getMessage(), userException.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
//
//		return new ResponseEntity<>(userExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}

}
