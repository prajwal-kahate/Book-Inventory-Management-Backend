package com.example.service;

import com.example.dto.LogInDto;
import com.example.dto.LogInResponseDto;
import com.example.exception.BookNotFoundException;

public interface LogInDtoService {
	LogInResponseDto login(LogInDto logInDto) throws BookNotFoundException;

	LogInDto logOut(String emailId);

}
