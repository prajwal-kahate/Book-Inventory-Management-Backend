package com.example.dto;

import lombok.Data;

@Data
public class LogInDto {

	private String emailId;
	private String password;
	private String role;
	private boolean isLogin;

}
