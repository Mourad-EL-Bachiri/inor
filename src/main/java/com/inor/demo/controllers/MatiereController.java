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

import com.inor.demo.requests.MatiereRequest;
import com.inor.demo.responses.MatiereResponse;
import com.inor.demo.sevices.MatiereService;
import com.inor.demo.shared.dto.MatiereDto;

@RequestMapping("/matieres")
@RestController
public class MatiereController {
	
	@Autowired
	MatiereService matiereService;

	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MatiereResponse> createMatiere(@RequestBody @Valid MatiereRequest matiereRequest){
		
		ModelMapper modelMapper = new ModelMapper();
		MatiereDto matiereDto = modelMapper.map(matiereRequest, MatiereDto.class);
		MatiereDto createdMatiere = matiereService.createMatiere(matiereDto);
		MatiereResponse matiereResponse = modelMapper.map(createdMatiere, MatiereResponse.class);
		return new ResponseEntity<MatiereResponse>(matiereResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MatiereResponse> getMatiereById(@PathVariable String id){
		MatiereDto matiereDto = matiereService.getMatiere(id);
		ModelMapper modelMapper = new ModelMapper();
		MatiereResponse matiereResponse = modelMapper.map(matiereDto, MatiereResponse.class);
		return new ResponseEntity<MatiereResponse>(matiereResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<MatiereResponse> getMatieres(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<MatiereResponse> matiereResponses = new ArrayList<>();
		List<MatiereDto> matieres = matiereService.getMatieres(page, limit);
		for(MatiereDto matiereDto: matieres) {
			ModelMapper modelMapper = new ModelMapper();
			MatiereResponse matiereResponse = modelMapper.map(matiereDto, MatiereResponse.class);
			matiereResponses.add(matiereResponse);
		}
		return matiereResponses;
	}
	
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MatiereResponse> updateMatiere(@PathVariable String id,@Valid@RequestBody MatiereRequest matiereRequest){
		ModelMapper modelMapper = new ModelMapper();
		MatiereDto matiereDto = modelMapper.map(matiereRequest, MatiereDto.class);
		MatiereDto updatedMat = matiereService.updateMatiere(id, matiereDto);
		MatiereResponse matiereResponse = modelMapper.map(updatedMat, MatiereResponse.class);
		return new ResponseEntity<MatiereResponse>(matiereResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteMatiere(@PathVariable String id) {
		matiereService.deleteMatiere(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
