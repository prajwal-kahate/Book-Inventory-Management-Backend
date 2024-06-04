package com.example.dto;

import lombok.Data;

@Data
public class LogInResponseDto {

	private String emailId;
	private String role;
	private boolean isLogin;
}
