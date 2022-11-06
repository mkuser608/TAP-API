package com.tapcell.mukesh.database.advice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ExceptionHandlerarg {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
		Map<String, String> errMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(eror -> {
			errMap.put(eror.getField(), eror.getDefaultMessage());
		});

		return errMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolationException(ConstraintViolationException exception) {
		Map<String, String> errMap = new HashMap<>();
		exception.getConstraintViolations().forEach(error -> {
			errMap.put(error.getInvalidValue().toString(), error.getMessage());
		});

		return errMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadCredentialsException.class)
	public Map<String, String> handleBadCredentialsException(BadCredentialsException exception) {
		Map<String, String> errMap = new HashMap<>();
		errMap.put("Varify And Send Again", exception.getMessage());

		return errMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleException(Exception exception) {
		Map<String, String> errMap = new HashMap<>();
		errMap.put(exception.getLocalizedMessage(), exception.getMessage());
		return errMap;

	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExpiredJwtException.class)
	public Map<String, String> handleExpiredJwtException(ExpiredJwtException exception) {
		Map<String, String> errMap = new HashMap<>();
		errMap.put(exception.getLocalizedMessage(), exception.getMessage());
		return errMap;

	}
	
	
	
	
}

