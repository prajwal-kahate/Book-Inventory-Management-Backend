package com.example.exception;

public class InvalidAuthorDataException extends RuntimeException {

	public InvalidAuthorDataException(String message) {
		super(message);
	}
}
