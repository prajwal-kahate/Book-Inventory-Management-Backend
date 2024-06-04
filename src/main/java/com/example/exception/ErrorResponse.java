package com.example.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ErrorResponse {

	private int status;

	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;

	public ErrorResponse(int status, String message, LocalDateTime timestamp) {
		super();
		this.status = status;

		this.message = message;
		this.timeStamp = timestamp;
	}

}
