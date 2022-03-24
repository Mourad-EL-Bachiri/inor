package com.inor.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inor.demo.exceptions.UserExeption;
import com.inor.demo.requests.UserRequest;
import com.inor.demo.responses.ErrorMessages;
import com.inor.demo.responses.UserResponse;
import com.inor.demo.sevices.UserService;
import com.inor.demo.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		if(userRequest.getFirstName().isEmpty()) throw new UserExeption(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		//UserDto userDto = new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto crateUser = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(crateUser, UserResponse.class);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUserByUserId(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<UserResponse> userResponses = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		for(UserDto userDto: users) 
		{
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userDto, user);
			userResponses.add(user);
		}
		return userResponses;		
	}

	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto userUpdated = userService.updateUser(id, userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userUpdated, userResponse);
		return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deletUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
