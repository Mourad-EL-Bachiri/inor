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

import com.inor.demo.requests.NiveauRequest;
import com.inor.demo.responses.NiveauResponse;
import com.inor.demo.sevices.NiveauService;
import com.inor.demo.shared.dto.NiveauDto;

@RequestMapping("/niveaux")
@RestController
public class NiveauController {
	
	@Autowired
	NiveauService niveauService;
	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<NiveauResponse> createNiveau(@RequestBody @Valid NiveauRequest niveauRequest){
		ModelMapper modelMapper = new ModelMapper();
		NiveauDto niveauDto = modelMapper.map(niveauRequest, NiveauDto.class);
		NiveauDto createdNiveau = niveauService.createNiveau(niveauDto);
		NiveauResponse niveauResponse = modelMapper.map(createdNiveau, NiveauResponse.class);
		return new ResponseEntity<NiveauResponse>(niveauResponse, HttpStatus.CREATED);
	}
	
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<NiveauResponse> getNiveau(@PathVariable String id){
		NiveauDto niveauDto = niveauService.getNiveau(id);
		ModelMapper modelMapper = new ModelMapper();
		NiveauResponse niveauResponse = modelMapper.map(niveauDto, NiveauResponse.class);
		return new ResponseEntity<NiveauResponse>(niveauResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<NiveauResponse> getNiveaux(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<NiveauResponse> niveauResponses = new ArrayList<>();
		List<NiveauDto> niveaux = niveauService.getNiveaux(page, limit);
		for(NiveauDto niveauDto: niveaux) {
			ModelMapper modelMapper = new ModelMapper();
			NiveauResponse niveauResponse = modelMapper.map(niveauDto, NiveauResponse.class);
			niveauResponses.add(niveauResponse);
		}
		return niveauResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<NiveauResponse> updateNiveau(@PathVariable String id, @Valid@RequestBody NiveauRequest niveauRequest){
		ModelMapper modelMapper = new ModelMapper();
		NiveauDto niveauDto = modelMapper.map(niveauRequest, NiveauDto.class);
		NiveauDto updatedNiveau = niveauService.updateNiveau(id, niveauDto);
		NiveauResponse niveauResponse = modelMapper.map(updatedNiveau, NiveauResponse.class);
		return new ResponseEntity<NiveauResponse>(niveauResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteNiveau(@PathVariable String id){
		niveauService.deleteNiveau(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	

}
