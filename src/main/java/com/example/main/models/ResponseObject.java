package com.example.main.models;

import java.text.SimpleDateFormat;

public class ResponseObject {
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	private String timestamp;
	private int statusCode;
	private String message;
	private Object data;
	public ResponseObject( int statusCode, String message, Object data) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		this.timestamp = sdf2.format(new java.util.Date());
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	
	
	
}
