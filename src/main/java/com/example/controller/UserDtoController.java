package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.service.UserDtoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserDtoController {

	@Autowired
	UserDtoService usetDtoService;

	@PostMapping("/add/user")
	UserDto addUser(@RequestBody UserDto userDto) {

		return usetDtoService.addUser(userDto);
	}

}
