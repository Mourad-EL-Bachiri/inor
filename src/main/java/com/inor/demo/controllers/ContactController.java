package com.inor.demo.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.inor.demo.requests.ContactParentRequest;
import com.inor.demo.responses.ContactResponse;
import com.inor.demo.sevices.ContactService;
import com.inor.demo.shared.dto.ContactDto;

@RequestMapping("/contacts")
@RestController
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContactResponse> createContact(@Valid@RequestBody ContactParentRequest contactParentRequest){
		
		ModelMapper modelMapper = new ModelMapper();
		ContactDto contactDto = modelMapper.map(contactParentRequest, ContactDto.class);
		ContactDto createdContact = contactService.createContact(contactDto);
	    ContactResponse contactResponse = modelMapper.map(createdContact, ContactResponse.class);
		return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContactResponse>getContact(@PathVariable String id) {
		ContactDto contactDto = contactService.getContact(id);
		ModelMapper modelMapper = new ModelMapper();
		ContactResponse contactResponse = modelMapper.map(contactDto, ContactResponse.class);
		return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.OK);
	}
	
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContactResponse> updateContact(@PathVariable String id, @RequestBody ContactParentRequest contactParentRequest){
		ModelMapper modelMapper = new ModelMapper();
		ContactDto contactDto = modelMapper.map(contactParentRequest, ContactDto.class);
		ContactDto updatedContact = contactService.updateContact(id, contactDto);
		ContactResponse contactResponse = modelMapper.map(updatedContact, ContactResponse.class);		
		return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteContact(@PathVariable String id){
		contactService.deletContact(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
