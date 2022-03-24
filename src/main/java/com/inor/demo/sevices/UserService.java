package com.inor.demo.sevices;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.inor.demo.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	UserDto updateUser(String userId, UserDto userDto);
	void deletUser(String userId);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	List<UserDto> getUsers(int page, int limit);
}
