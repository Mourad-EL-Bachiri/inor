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

import com.inor.demo.requests.SecretaireRequest;
import com.inor.demo.responses.SecretaireResponse;
import com.inor.demo.sevices.SecretaireService;
import com.inor.demo.shared.dto.SecretaireDto;

@RequestMapping("/secretaires")
@RestController
public class SecretaireController {
	
	@Autowired
	SecretaireService secretaireService;

	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SecretaireResponse> createSecretaire(@RequestBody@Valid SecretaireRequest secretaireRequest) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		SecretaireDto secretaireDto = modelMapper.map(secretaireRequest, SecretaireDto.class);
		SecretaireDto crateSecretaire = (SecretaireDto) secretaireService.createUser(secretaireDto);
		SecretaireResponse secretaireResponse = modelMapper.map(crateSecretaire, SecretaireResponse.class);
		return new ResponseEntity<>(secretaireResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SecretaireResponse> getSecretaire(@PathVariable String id){
		ModelMapper modelMapper = new ModelMapper();
		SecretaireDto secretaireDto = secretaireService.getSecretaire(id);
		SecretaireResponse secretaireResponse = modelMapper.map(secretaireDto, SecretaireResponse.class);
		return new ResponseEntity<SecretaireResponse>(secretaireResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<SecretaireResponse> getSecretaires(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<SecretaireResponse> secretaireResponses = new ArrayList<>();
		List<SecretaireDto> secretaires = secretaireService.getSecretaires(page, limit);
		for(SecretaireDto secretaireDto: secretaires) {
			ModelMapper modelMapper = new ModelMapper();
			SecretaireResponse secretaireResponse = modelMapper.map(secretaireDto, SecretaireResponse.class);
			secretaireResponses.add(secretaireResponse);
		}
		return secretaireResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SecretaireResponse> updateSecretaire(@PathVariable String id, @Valid@RequestBody SecretaireRequest secretaireRequest){
		ModelMapper modelMapper = new ModelMapper();
		SecretaireDto secretaireDto = modelMapper.map(secretaireRequest, SecretaireDto.class);
		SecretaireDto updatedSecretaire = secretaireService.updateSecretaire(id,secretaireDto);
		SecretaireResponse secretaireResponse = modelMapper.map(updatedSecretaire, SecretaireResponse.class);
		return new ResponseEntity<SecretaireResponse>(secretaireResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteSecretaire(@PathVariable String id){
		secretaireService.deleteSecretaire(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
