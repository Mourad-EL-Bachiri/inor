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

import com.inor.demo.requests.EtudiantRequest;
import com.inor.demo.responses.EtudianResponse;
import com.inor.demo.sevices.EtudiantService;
import com.inor.demo.shared.dto.EtudiantDto;

@RequestMapping("/etudiants")
@RestController
public class EtudiantController {
	
	@Autowired
	EtudiantService etudiantService;
	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EtudianResponse> createEtudiant(@Valid@RequestBody EtudiantRequest etudiantRequest){
		ModelMapper modelMapper = new ModelMapper();
		EtudiantDto etudiantDto = modelMapper.map(etudiantRequest, EtudiantDto.class);
		EtudiantDto createdEtudiant = etudiantService.createEtu(etudiantDto);
		EtudianResponse etudianResponse = modelMapper.map(createdEtudiant, EtudianResponse.class);
		return new ResponseEntity<EtudianResponse>(etudianResponse, HttpStatus.CREATED);
	}
	
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EtudianResponse> getEtudiant(@PathVariable String id){
		ModelMapper modelMapper = new ModelMapper();
		EtudiantDto etudiantDto = etudiantService.getEtu(id);
		EtudianResponse etudianResponse = modelMapper.map(etudiantDto, EtudianResponse.class);
		return new ResponseEntity<EtudianResponse>(etudianResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<EtudianResponse> getEtudiants(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<EtudianResponse> etudianResponses = new ArrayList<>();
		List<EtudiantDto> etudiants = etudiantService.getAllEtu(page, limit);
		for(EtudiantDto etudiantDto: etudiants) {
			ModelMapper modelMapper = new ModelMapper();
			EtudianResponse etudianResponse = modelMapper.map(etudiantDto, EtudianResponse.class);
			etudianResponses.add(etudianResponse);
		}
		return etudianResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EtudianResponse> updateEtudiant(@PathVariable String id, @Valid@RequestBody EtudiantRequest etudiantRequest){
		ModelMapper modelMapper = new ModelMapper();
		EtudiantDto etudiantDto = modelMapper.map(etudiantRequest, EtudiantDto.class);
		EtudiantDto updatedEtu = etudiantService.updateEtu(id,etudiantDto);
		EtudianResponse etudianResponse = modelMapper.map(updatedEtu, EtudianResponse.class);
		return new ResponseEntity<EtudianResponse>(etudianResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteEtudiant(@PathVariable String id){
		etudiantService.deleteEtu(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
