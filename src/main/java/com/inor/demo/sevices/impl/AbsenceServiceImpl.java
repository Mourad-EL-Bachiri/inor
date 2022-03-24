package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.AbsenceEntity;
import com.inor.demo.reposetries.AbsenceRepository;
import com.inor.demo.sevices.AbsenceService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.AbsenceDto;

@Service
public class AbsenceServiceImpl implements AbsenceService{

	@Autowired
	AbsenceRepository absenceRepository;
	
	@Autowired
	Utils utils;
	
	
	@Override
	public AbsenceDto createAbsence(AbsenceDto absenceDto) {
		ModelMapper modelMapper = new ModelMapper();
		AbsenceEntity absenceEntity = modelMapper.map(absenceDto, AbsenceEntity.class);
		absenceEntity.setAbsenceId(utils.generatId(32));
		AbsenceEntity createdAbsence = absenceRepository.save(absenceEntity);
		AbsenceDto absence = modelMapper.map(createdAbsence, AbsenceDto.class);
		return absence;
	}


	@Override
	public AbsenceDto getAbsence(String id) {
		AbsenceEntity absence = absenceRepository.findByAbsenceId(id);
		if(absence == null) throw new RuntimeException("Absence not found!!");
		ModelMapper modelMapper = new ModelMapper();
		AbsenceDto absenceDto = modelMapper.map(absence, AbsenceDto.class);
		return absenceDto;
	}


	@Override
	public List<AbsenceDto> getAbsences(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<AbsenceDto> absenceDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<AbsenceEntity> absencePage = absenceRepository.findAll(pageableRequest);
		List<AbsenceEntity> absences = absencePage.getContent();
		for(AbsenceEntity absenceEntity: absences) {
			ModelMapper modelMapper = new ModelMapper();
			AbsenceDto absenceDto = modelMapper.map(absenceEntity, AbsenceDto.class);
			absenceDtos.add(absenceDto);
		}
		return absenceDtos;
	}


	@Override
	public AbsenceDto updateAbsence(String id, AbsenceDto absenceDto) {
		AbsenceEntity absence = absenceRepository.findByAbsenceId(id);
		if(absence == null) throw new RuntimeException("Absence not found!!");
		absence.setDate(absenceDto.getDate());
		absence.setHeure(absenceDto.getHeure());
		absence.setSeance(absenceDto.getSeance());
		absence.setJustif(absenceDto.getJustif());
		AbsenceEntity updatedAbsence = absenceRepository.save(absence);
		ModelMapper modelMapper = new ModelMapper();
		AbsenceDto absenceDt = modelMapper.map(updatedAbsence, AbsenceDto.class);
		return absenceDt;
	}


	@Override
	public void deleteAbsence(String id) {
		AbsenceEntity absence = absenceRepository.findByAbsenceId(id);
		if(absence == null) throw new RuntimeException("Absence not found!!");
		absenceRepository.delete(absence);
		
	}

}
