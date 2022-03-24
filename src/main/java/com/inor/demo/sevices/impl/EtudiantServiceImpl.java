package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.EtudiantEntity;
import com.inor.demo.entities.RoleEntity;
import com.inor.demo.entities.UserEntity;
import com.inor.demo.reposetries.EtudiantRepository;
import com.inor.demo.reposetries.RoleRepository;
import com.inor.demo.sevices.EtudiantService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.ContactDto;
import com.inor.demo.shared.dto.EtudiantDto;
import com.inor.demo.shared.dto.RoleDto;

@Service
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public EtudiantDto createEtu(EtudiantDto etudiantDto) {
		EtudiantEntity etudiant = etudiantRepository.findByCne(etudiantDto.getCne());
		if(etudiant != null) throw new RuntimeException("Etudtiant Alredy Exist");
		RoleDto roleDto = etudiantDto.getRole();
		// add contact parents
		ContactDto contactDto = etudiantDto.getContact();
		contactDto.setEtudiant(etudiantDto);
		contactDto.setContactId(utils.generatId(32));
		etudiantDto.setContact(contactDto);
		
		RoleEntity role = roleRepository.findByRole(roleDto.getRole());
		if( role == null ) throw new RuntimeException("role not found!!!");
		ModelMapper modelMapper = new ModelMapper();
		EtudiantEntity etudiantEntity = modelMapper.map(etudiantDto, EtudiantEntity.class);
		List<UserEntity> etudiants = Collections.singletonList(etudiantEntity);
		role.setUsers(etudiants);
		etudiantEntity.setRole(role);
		
		etudiantEntity.setUserId(utils.generatId(32));
		etudiantEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(etudiantDto.getPassword()));
		EtudiantEntity createdEtu = etudiantRepository.save(etudiantEntity);
		EtudiantDto etuDto = modelMapper.map(createdEtu, EtudiantDto.class);
		return etuDto;
	}

	@Override
	public EtudiantDto getEtu(String id) {
		EtudiantEntity etudiant = etudiantRepository.findByUserId(id);
		if(etudiant == null) throw new RuntimeException("Etudtiant noot found");
		ModelMapper modelMapper = new ModelMapper();
		EtudiantDto etudiantDto = modelMapper.map(etudiant, EtudiantDto.class);
		return etudiantDto;
	}

	@Override
	public List<EtudiantDto> getAllEtu(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<EtudiantDto> etudiantDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<EtudiantEntity> etudiantPage = etudiantRepository.findAll(pageableRequest);
		List<EtudiantEntity> etudiants = etudiantPage.getContent();
		for(EtudiantEntity etudiantEntity: etudiants) {
			ModelMapper modelMapper = new ModelMapper();
			EtudiantDto etudiantDto = modelMapper.map(etudiantEntity, EtudiantDto.class);
			etudiantDtos.add(etudiantDto);
		}
		return etudiantDtos;
	}

	@Override
	public EtudiantDto updateEtu(String id, EtudiantDto etudiantDto) {
		EtudiantEntity etudiant = etudiantRepository.findByUserId(id);
		if(etudiant == null) throw new RuntimeException("Etudtiant not found");
		etudiant.setEmail(etudiantDto.getEmail());
		etudiant.setFirstName(etudiantDto.getFirstName());
		etudiant.setLastName(etudiantDto.getLastName());
		etudiant.setCne(etudiantDto.getCne());
		etudiant.setDate_nais(etudiantDto.getDate_nais());
		EtudiantEntity updatedEtu = etudiantRepository.save(etudiant);
		ModelMapper modelMapper = new ModelMapper();
		EtudiantDto etutDto = modelMapper.map(updatedEtu, EtudiantDto.class);
		return etutDto;
	}

	@Override
	public void deleteEtu(String id) {
		EtudiantEntity etudiant = etudiantRepository.findByUserId(id);
		if(etudiant == null) throw new RuntimeException("Etudtiant not found");
		etudiantRepository.delete(etudiant);
	}

	
}
