package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LogInDto;
import com.example.dto.LogInResponseDto;
import com.example.exception.BookNotFoundException;
import com.example.service.LogInDtoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LogInController {

	@Autowired
	LogInDtoService logInDtoService;

	@PostMapping("/login")
	LogInResponseDto login(@RequestBody LogInDto logInDto) throws BookNotFoundException {
		System.out.println(logInDto);
		return logInDtoService.login(logInDto);
	}

	@GetMapping("/logout/{emailId}")
	LogInDto logOut(@PathVariable String emailId) {
		System.out.println(emailId);
		return logInDtoService.logOut(emailId);
	}

}
