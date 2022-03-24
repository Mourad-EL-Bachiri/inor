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

import com.inor.demo.requests.AbsenceRequest;
import com.inor.demo.responses.AbsenceResponse;
import com.inor.demo.sevices.AbsenceService;
import com.inor.demo.shared.dto.AbsenceDto;

@RequestMapping("/absences")
@RestController
public class AbsenceController {

	@Autowired
	AbsenceService absenceService;
	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AbsenceResponse> createAbsence(@Valid@RequestBody AbsenceRequest absenceRequest){
		ModelMapper modelMapper = new ModelMapper();
		AbsenceDto absenceDto = modelMapper.map(absenceRequest, AbsenceDto.class);
		AbsenceDto createdAbsense = absenceService.createAbsence(absenceDto);
		AbsenceResponse absenceResponse = modelMapper.map(createdAbsense, AbsenceResponse.class);
		return new ResponseEntity<AbsenceResponse>(absenceResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AbsenceResponse> getAbsence(@PathVariable String id){
		AbsenceDto absenceDto = absenceService.getAbsence(id);
		ModelMapper modelMapper = new ModelMapper();
		AbsenceResponse absenceResponse = modelMapper.map(absenceDto, AbsenceResponse.class);
		return new ResponseEntity<AbsenceResponse>(absenceResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<AbsenceResponse> getAbsences(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<AbsenceResponse> absenceResponses = new ArrayList<>();
		List<AbsenceDto> absences = absenceService.getAbsences(page, limit);
		for(AbsenceDto absenceDto: absences) {
			ModelMapper modelMapper = new ModelMapper();
			AbsenceResponse absenceResponse = modelMapper.map(absenceDto, AbsenceResponse.class);
			absenceResponses.add(absenceResponse);
		}
		return absenceResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AbsenceResponse> updateAbsence(@PathVariable String id, @Valid@RequestBody AbsenceRequest absenceRequest){
		ModelMapper modelMapper = new ModelMapper();
		AbsenceDto absenceDto = modelMapper.map(absenceRequest, AbsenceDto.class);
		AbsenceDto updatedAbsence = absenceService.updateAbsence(id, absenceDto);
		AbsenceResponse absenceResponse = modelMapper.map(updatedAbsence, AbsenceResponse.class);
		return new ResponseEntity<AbsenceResponse>(absenceResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteAbsence(@PathVariable String id){
		absenceService.deleteAbsence(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
