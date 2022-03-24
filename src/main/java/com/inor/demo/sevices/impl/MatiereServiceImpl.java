package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.MatiereEntity;
import com.inor.demo.entities.ProfEntity;
import com.inor.demo.reposetries.MatiereRepository;
import com.inor.demo.reposetries.ProfRepository;
import com.inor.demo.sevices.MatiereService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.MatiereDto;
import com.inor.demo.shared.dto.NiveauDto;
import com.inor.demo.shared.dto.ProfDto;

@Service
public class MatiereServiceImpl implements MatiereService {
	
	@Autowired
	MatiereRepository matiereRepository;
	
	@Autowired
	ProfRepository profRepository;
	
	
	@Autowired
	Utils utils;

	@Override
	public MatiereDto createMatiere(MatiereDto matiereDto) {
		MatiereEntity checkMatiere = matiereRepository.findByNameMat(matiereDto.getNameMat());
		if(checkMatiere != null) throw new RuntimeException("mat Already exist");
		
		ProfDto profDto = matiereDto.getProf();
		ProfEntity prof = profRepository.findByEmail(profDto.getEmail());
		if(prof == null) throw new RuntimeException("prof n'existe pas");
		
			for(int i=0 ; i < matiereDto.getNiveaux().size() ; i++) {
			NiveauDto niveau = matiereDto.getNiveaux().get(i);
			niveau.setMatieres(Collections.singletonList(matiereDto));
			niveau.setNiveauId(utils.generatId(32));
			matiereDto.getNiveaux().set(i, niveau);
		}
		ModelMapper modelMapper = new ModelMapper();
		MatiereEntity matiereEntity = modelMapper.map(matiereDto, MatiereEntity.class);
		prof.setMatieres(Collections.singletonList(matiereEntity));
		matiereEntity.setProf(prof);
		matiereEntity.setMatiereId(utils.generatId(32));
		MatiereEntity newMat = matiereRepository.save(matiereEntity);
		MatiereDto matiere = modelMapper.map(newMat, MatiereDto.class);
		return matiere;
	}

	@Override
	public MatiereDto getMatiere(String id) {
		MatiereEntity checkMatiere = matiereRepository.findByMatiereId(id);
		if(checkMatiere == null) throw new RuntimeException("matiere not found");
		ModelMapper modelMapper = new ModelMapper();
		MatiereDto matiereDto = modelMapper.map(checkMatiere, MatiereDto.class);
		return matiereDto;
	}

	@Override
	public MatiereDto updateMatiere(String id, MatiereDto matiereDto) {
		MatiereEntity matiere = matiereRepository.findByMatiereId(id);
		if(matiere == null) throw new RuntimeException("matiere not found");
		matiere.setNameMat(matiereDto.getNameMat());
		matiere.setPrix(matiereDto.getPrix());
		MatiereEntity matiereEntity = matiereRepository.save(matiere);
		ModelMapper modelMapper = new ModelMapper();
		MatiereDto mat = modelMapper.map(matiereEntity, MatiereDto.class);
		return mat;
	}

	@Override
	public void deleteMatiere(String id) {
		MatiereEntity matiere = matiereRepository.findByMatiereId(id);
		if(matiere == null) throw new RuntimeException("matiere not found");
		matiereRepository.delete(matiere);
	}

	@Override
	public List<MatiereDto> getMatieres(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<MatiereDto> matiereDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<MatiereEntity> matierePage = matiereRepository.findAll(pageableRequest);
		List<MatiereEntity> matieres = matierePage.getContent();
		for(MatiereEntity matiereEntity: matieres) {
			ModelMapper modelMapper = new ModelMapper();
			MatiereDto matiereDto = modelMapper.map(matiereEntity, MatiereDto.class);
			matiereDtos.add(matiereDto);
		}
		return matiereDtos;
	}

}
