package com.example.dto;

import com.example.entity.PermRole;

import lombok.Data;

@Data
public class UserDto {

	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String emailId;
	private String password;
	private int rollNo;

}
