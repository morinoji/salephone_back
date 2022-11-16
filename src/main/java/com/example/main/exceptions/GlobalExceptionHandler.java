package com.example.main.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors=new ArrayList<>();
		errors.add(ex.getMessage());
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, 400 , "Data mismatch!", errors);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}

    
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> handleNotFound(NoDataFoundException ex, HttpServletRequest request){
		ApiError error=new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND, 404 , "Data not found!", null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error, error.getStatus());
		return response;
	}
	

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> contrainViolate(ConstraintViolationException ex){
		List<String> errors=ex.getConstraintViolations().stream().map(x->x.getPropertyPath().toString().split("\\.")[1]+" "+ x.getMessage()).collect(Collectors.toList());
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, 400 , "Invalid Arguments!", errors);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> badCredentials(BadCredentialsException ex){
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, 401 , ex.getMessage(), null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> badCredentials(SQLIntegrityConstraintViolationException ex){
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, 400 , "Email đã trùng!", null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseEntity<Object> outOfBound(IndexOutOfBoundsException ex){
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, 500 , ex.getMessage(), null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}
	
	
	@ExceptionHandler(InvalidRequestBodyException.class)
	public ResponseEntity<Object> invalidFormatJson(InvalidRequestBodyException ex){
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, 400 , "Invalid Request Body Type !", null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}
	
	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<Object> badCredentialCustom(BadCredentialException ex){
		ApiError error1=new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, 500 , "Đăng nhập thất bại!", null);
		ResponseEntity<Object> response=new ResponseEntity<Object>(error1, error1.getStatus());
		return response;
	}
}
