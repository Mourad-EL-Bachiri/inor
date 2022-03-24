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

import com.inor.demo.entities.EtudiantEntity;
import com.inor.demo.entities.MatiereEntity;
import com.inor.demo.entities.SeanceEntity;
import com.inor.demo.reposetries.EtudiantRepository;
import com.inor.demo.reposetries.MatiereRepository;
import com.inor.demo.reposetries.SeanceRepository;
import com.inor.demo.sevices.SeanceService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.EtudiantDto;
import com.inor.demo.shared.dto.MatiereDto;
import com.inor.demo.shared.dto.SeanceDto;

@Service
public class SeanceServiceImpl implements SeanceService {
	
	@Autowired
	SeanceRepository seanceRepository;
	
	@Autowired
	MatiereRepository matiereRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	Utils utils;

	@Override
	public SeanceDto createSeance(SeanceDto seanceDto) {
		
		MatiereDto matiereDto = seanceDto.getMatiere();
		MatiereEntity matiere = matiereRepository.findByNameMat(matiereDto.getNameMat());
		if(matiere == null) throw new RuntimeException("Matiere not found");
		
		List<EtudiantDto> etudiantDtos = seanceDto.getEtudiants();
		
		
		ModelMapper modelMapper = new ModelMapper();
		SeanceEntity seanceEntity = modelMapper.map(seanceDto, SeanceEntity.class);
		//add etudiants
		for(int i = 0 ; i < etudiantDtos.size() ; i++) {
			EtudiantDto etudiantDto = seanceDto.getEtudiants().get(i);
			EtudiantEntity etudiant = etudiantRepository.findByCne(etudiantDto.getCne());
			if(etudiant == null) throw new RuntimeException("Etudiant " + etudiantDto.getCne() + " not found");
			etudiant.setSeances(Collections.singletonList(seanceEntity));
			seanceEntity.getEtudiants().set(i, etudiant);
		}
		matiere.setSeances(Collections.singletonList(seanceEntity));
		seanceEntity.setMatiere(matiere);
		seanceEntity.setSeanceId(utils.generatId(32));
		SeanceEntity createdSeance = seanceRepository.save(seanceEntity);
		SeanceDto seance = modelMapper.map(createdSeance, SeanceDto.class);
		return seance;
	}

	@Override
	public SeanceDto getSeance(String id) {
		SeanceEntity seance = seanceRepository.findBySeanceId(id);
		if(seance == null) throw new RuntimeException("Seance not found !!");
		ModelMapper modelMapper = new ModelMapper();
		SeanceDto seanceDto = modelMapper.map(seance, SeanceDto.class);
		return seanceDto;
	}

	@Override
	public List<SeanceDto> getSeances(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<SeanceDto> seanceDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<SeanceEntity> seancePage = seanceRepository.findAll(pageableRequest);
		List<SeanceEntity> seances = seancePage.getContent();
		for(SeanceEntity seanceEntity: seances) {
			ModelMapper modelMapper = new ModelMapper();
			SeanceDto seanceDto = modelMapper.map(seanceEntity, SeanceDto.class);
			seanceDtos.add(seanceDto);
		}
		return seanceDtos;
	}

	@Override
	public SeanceDto updateSeance(String id, SeanceDto seanceDto) {
		SeanceEntity seance = seanceRepository.findBySeanceId(id);
		if(seance == null) throw new RuntimeException("Seance not found !!");
		seance.setDate_seance(seanceDto.getDate_seance());
		seance.setHeure(seanceDto.getHeure());
		SeanceEntity updatedSeance = seanceRepository.save(seance);
		ModelMapper modelMapper = new ModelMapper();
		SeanceDto seanceDt = modelMapper.map(updatedSeance, SeanceDto.class);
		return seanceDt;
	}

	@Override
	public void deleteSeance(String id) {
		SeanceEntity seance = seanceRepository.findBySeanceId(id);
		if(seance == null) throw new RuntimeException("Seance not found !!");
		seanceRepository.delete(seance);
	}

}
