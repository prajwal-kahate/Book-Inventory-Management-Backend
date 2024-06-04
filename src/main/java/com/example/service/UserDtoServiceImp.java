package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PermRoleDao;
import com.example.dao.UserDao;
import com.example.dto.UserDto;
import com.example.entity.Book;
import com.example.entity.PermRole;
import com.example.entity.User;
import com.example.exception.CategoryNotFoundException;

@Service
public class UserDtoServiceImp implements UserDtoService {

	@Autowired
	UserDao userDao;

	@Autowired
	PermRoleDao permRoleDao;

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = new User();
		user.setLastName(userDto.getLastName());
		user.setFirstName(userDto.getFirstName());
		user.setUserName(userDto.getEmailId());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		Optional<PermRole> opt = permRoleDao.findById(1);
		if (opt.isPresent()) {
			PermRole permRole = opt.get();
			user.setPermRole(permRole);
		}

		User updatedUser = userDao.save(user);
		UserDto updatedDtoUser = new UserDto();
		updatedDtoUser.setLastName(updatedUser.getLastName());
		updatedDtoUser.setFirstName(updatedUser.getFirstName());
		updatedDtoUser.setEmailId(updatedUser.getUserName());
		updatedDtoUser.setPassword(updatedUser.getPassword());
		updatedDtoUser.setPhoneNumber(updatedUser.getPhoneNumber());

		return updatedDtoUser;
	}

}
