package com.example.main.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private Integer statusCode;
	private String message;
	private List errors;
	
	
	public ApiError(LocalDateTime timestamp, HttpStatus status, Integer statusCode, String message, List errors) {
		this.timestamp = timestamp;
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.errors = errors;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List getErrors() {
		return errors;
	}
	public void setErrors(List errors) {
		this.errors = errors;
	}

	
	
}
