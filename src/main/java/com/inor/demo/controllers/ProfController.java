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

import com.inor.demo.requests.ProfRequest;
import com.inor.demo.responses.ProfResponse;
import com.inor.demo.sevices.ProfService;
import com.inor.demo.shared.dto.ProfDto;

@RequestMapping("/professeurs")
@RestController
public class ProfController {

	@Autowired
	ProfService profService;
	
	@PostMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProfResponse> createProf(@Valid@RequestBody ProfRequest profRequest){
		ModelMapper modelMapper = new ModelMapper();
		ProfDto profDto = modelMapper.map(profRequest, ProfDto.class);
		ProfDto createdProf = profService.createProf(profDto);
		ProfResponse profResponse = modelMapper.map(createdProf, ProfResponse.class);
		return new ResponseEntity<ProfResponse>(profResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProfResponse> getProf(@PathVariable String id){
		ModelMapper modelMapper = new ModelMapper();
		ProfDto prof = profService.getProf(id);
		ProfResponse profResponse = modelMapper.map(prof, ProfResponse.class);
		return new ResponseEntity<ProfResponse>(profResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ProfResponse> getAllProfs(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "4") int limit){
		List<ProfResponse> profResponses = new ArrayList<>();
		List<ProfDto> profs = profService.getAllprofs(page, limit);
		for(ProfDto profDto: profs) {
			ModelMapper modelMapper = new ModelMapper();
			ProfResponse profResponse = modelMapper.map(profDto, ProfResponse.class);
			profResponses.add(profResponse);
		}
		return profResponses;
	}
	
	@PutMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProfResponse> updateProf(@PathVariable String id, @Valid@RequestBody ProfRequest profRequest){
		ModelMapper modelMapper = new ModelMapper();
		ProfDto profDto = modelMapper.map(profRequest, ProfDto.class);
		ProfDto updatedProf = profService.updateProf(id,profDto);
		ProfResponse profResponse = modelMapper.map(updatedProf, ProfResponse.class);
		return new ResponseEntity<ProfResponse>(profResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> DeleteProf(@PathVariable String id){
		profService.deleteProf(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
