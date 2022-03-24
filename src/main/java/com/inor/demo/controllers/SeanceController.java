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

import com.inor.demo.requests.SeanceRequest;
import com.inor.demo.responses.SeanceResponse;
import com.inor.demo.sevices.SeanceService;
import com.inor.demo.shared.dto.SeanceDto;

@RequestMapping("/seances")
@RestController
public class SeanceController {
	
	@Autowired
	SeanceService seanceService;

	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SeanceResponse> createSeance (@Valid@RequestBody SeanceRequest seanceRequest){
		ModelMapper modelMapper = new ModelMapper();
		SeanceDto seanceDto = modelMapper.map(seanceRequest, SeanceDto.class);
		SeanceDto createdSeance = seanceService.createSeance(seanceDto);
		SeanceResponse seanceResponse = modelMapper.map(createdSeance, SeanceResponse.class);
		return new ResponseEntity<SeanceResponse>(seanceResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SeanceResponse> getSeance(@PathVariable String id){
		SeanceDto senDto = seanceService.getSeance(id);
		ModelMapper modelMapper = new ModelMapper();
		SeanceResponse senResponse = modelMapper.map(senDto, SeanceResponse.class);
		return new ResponseEntity<SeanceResponse>(senResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<SeanceResponse> getSeances(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<SeanceResponse> seanceResponses = new ArrayList<>();
		List<SeanceDto> seances = seanceService.getSeances(page, limit);
		for (SeanceDto seanceDto: seances) {
			ModelMapper modelMapper = new ModelMapper();
			SeanceResponse seanceResponse = modelMapper.map(seanceDto, SeanceResponse.class);
			seanceResponses.add(seanceResponse);
		}
		return seanceResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SeanceResponse> updateSeance(@PathVariable String id, @Valid @RequestBody SeanceRequest seanceRequest){
		ModelMapper modelMapper = new ModelMapper();
		SeanceDto seanceDto = modelMapper.map(seanceRequest, SeanceDto.class);
		SeanceDto updatedSeance = seanceService.updateSeance(id, seanceDto);
		SeanceResponse seanceResponse = modelMapper.map(updatedSeance, SeanceResponse.class);
		return new ResponseEntity<SeanceResponse>(seanceResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteSeance(@PathVariable String id){
		seanceService.deleteSeance(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
