package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.dto.LogInDto;
import com.example.dto.LogInResponseDto;
import com.example.entity.User;
import com.example.exception.BookNotFoundException;

@Service
public class LogInDtoServiceImp implements LogInDtoService {

	@Autowired
	UserDao userDao;

	@Override
	public LogInResponseDto login(LogInDto logInDto) throws BookNotFoundException {
//		System.out.println(logInDto);

		User user = userDao.findByUserName(logInDto.getEmailId());
//	    System.out.println(user);

		if (user != null) {
			if (logInDto.getPassword().equals(user.getPassword())
					&& logInDto.getRole().equals(user.getPermRole().getPermRole())) {
				user.setLogin(true);
				User updatedUser = userDao.save(user);

				LogInResponseDto logInDto1 = new LogInResponseDto();
				logInDto1.setLogin(updatedUser.isLogin());
				logInDto1.setEmailId(updatedUser.getUserName());
				logInDto1.setRole(updatedUser.getPermRole().getPermRole());
				return logInDto1;
			} else {
				throw new BookNotFoundException("Book nahi ");
			}
		} else {
			throw new BookNotFoundException("Book nahi");
		}
	}

	@Override
	public LogInDto logOut(String emailId) {
		User user = userDao.findByUserName(emailId);
		System.out.println(user);

		if (user != null) {
			user.setLogin(false);
			User updatedUser = userDao.save(user);

			LogInDto logInDto1 = new LogInDto();
			logInDto1.setLogin(updatedUser.isLogin());
			logInDto1.setEmailId(updatedUser.getUserName());
			logInDto1.setPassword(updatedUser.getPassword());
			logInDto1.setRole(updatedUser.getPermRole().getPermRole());
			return logInDto1;
		} else {
			System.out.println("User not found");
		}

		return null;
	}

}
