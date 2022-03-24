package com.inor.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.modelmapper.ModelMapper;
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

import com.inor.demo.requests.RoleRequest;
import com.inor.demo.responses.RoleResponse;
import com.inor.demo.sevices.RoleService;
import com.inor.demo.shared.dto.RoleDto;

@RequestMapping("/roles")
@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RoleResponse> createRole(@Valid@RequestBody RoleRequest roleRequest){
		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(roleRequest, RoleDto.class);
		RoleDto createdRole = roleService.createRole(roleDto);
		RoleResponse roleResponse = modelMapper.map(createdRole, RoleResponse.class);
		return new ResponseEntity<RoleResponse>(roleResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RoleResponse> getRole(@PathVariable String id){
		RoleDto roleDto = roleService.getRole(id);
		ModelMapper modelMapper = new ModelMapper();
		RoleResponse roleResponse = modelMapper.map(roleDto, RoleResponse.class);
		return new ResponseEntity<RoleResponse>(roleResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<RoleResponse> getRoles(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<RoleResponse> roleResponses = new ArrayList<>();
		List<RoleDto> roles = roleService.getRoles(page, limit);
		for(RoleDto roleDto : roles) {
			ModelMapper modelMapper = new ModelMapper();
			RoleResponse roleResponse = modelMapper.map(roleDto, RoleResponse.class);
			roleResponses.add(roleResponse);
		}
		return roleResponses;
	}

	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }) ResponseEntity<RoleResponse> updateRole(@PathVariable String id, @Valid@RequestBody RoleRequest roleRequest){
		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(roleRequest, RoleDto.class);
		RoleDto updatedRole = roleService.updateRole(id,roleDto);
		RoleResponse roleResponse = modelMapper.map(updatedRole, RoleResponse.class);
		return new ResponseEntity<RoleResponse>(roleResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteRole (@PathVariable String id){
		roleService.deleteRole(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
