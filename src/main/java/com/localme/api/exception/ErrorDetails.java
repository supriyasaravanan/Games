package com.localme.api.exception;

import java.util.Date;

public class ErrorDetails {
	
	int status;
	private String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorDetails(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		
	}
	
	
	

	
}
