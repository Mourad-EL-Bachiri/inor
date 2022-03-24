package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.NiveauEntity;
import com.inor.demo.reposetries.NiveauRepository;
import com.inor.demo.sevices.NiveauService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.NiveauDto;

@Service
public class NiveauServiceImpl implements NiveauService{

	@Autowired
	NiveauRepository niveauRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public NiveauDto createNiveau(NiveauDto niveauDto) {
		NiveauEntity checkNiveau = niveauRepository.findByNiveau(niveauDto.getNiveau());
		if(checkNiveau != null) throw new RuntimeException("niveau already exist");
		ModelMapper modelMapper = new ModelMapper();
		NiveauEntity niveauEntity = modelMapper.map(niveauDto, NiveauEntity.class);
		niveauEntity.setNiveauId(utils.generatId(32));
		NiveauEntity newNiveau = niveauRepository.save(niveauEntity);
		NiveauDto niveau = modelMapper.map(newNiveau, NiveauDto.class);
		return niveau;
	}

	@Override
	public NiveauDto getNiveau(String id) {
		NiveauEntity niveau = niveauRepository.findByNiveauId(id);
		if(niveau == null ) throw new RuntimeException("niveau not found");
		ModelMapper modelMapper = new ModelMapper();
		NiveauDto niveauDto = modelMapper.map(niveau, NiveauDto.class);
		return niveauDto;
	}

	@Override
	public List<NiveauDto> getNiveaux(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<NiveauDto> niveauDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<NiveauEntity> niveauPage = niveauRepository.findAll(pageableRequest);
		List<NiveauEntity> niveaux = niveauPage.getContent();
		for(NiveauEntity niveauEntity: niveaux) {
			ModelMapper modelMapper = new ModelMapper();
			NiveauDto niveauDto = modelMapper.map(niveauEntity, NiveauDto.class);
			niveauDtos.add(niveauDto);
		}
		return niveauDtos;
	}

	@Override
	public NiveauDto updateNiveau(String id, NiveauDto niveauDto) {
		NiveauEntity niveau = niveauRepository.findByNiveauId(id);
		if(niveau == null ) throw new RuntimeException("niveau not found");
		niveau.setNiveau(niveauDto.getNiveau());
		NiveauEntity niveauEntity = niveauRepository.save(niveau);
		ModelMapper modelMapper = new ModelMapper();
		NiveauDto updatedNiveau = modelMapper.map(niveauEntity, NiveauDto.class);
		return updatedNiveau;
	}

	@Override
	public void deleteNiveau(String id) {
		NiveauEntity niveau = niveauRepository.findByNiveauId(id);
		if(niveau == null ) throw new RuntimeException("niveau not found");
		niveauRepository.delete(niveau);
		
	}

}
