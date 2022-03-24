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

import com.inor.demo.entities.RoleEntity;
import com.inor.demo.entities.SecretaireEntity;
import com.inor.demo.entities.UserEntity;
import com.inor.demo.reposetries.RoleRepository;
import com.inor.demo.reposetries.SecretaireRepository;
import com.inor.demo.sevices.SecretaireService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.RoleDto;
import com.inor.demo.shared.dto.SecretaireDto;

@Service
public class SecretaireServiceImpl implements SecretaireService {
	
	@Autowired SecretaireRepository secretaireRepository;
	
	@Autowired RoleRepository roleRepository;
	
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired Utils utils;


	@Override
	public SecretaireDto createUser(SecretaireDto secretaireDto) {
		SecretaireEntity checkSecretaire = secretaireRepository.findByEmail(secretaireDto.getEmail());
		if( checkSecretaire != null ) throw new RuntimeException("Secretaire Already Exists!!!");
		RoleDto roleDto = secretaireDto.getRole();
		RoleEntity role = roleRepository.findByRole(roleDto.getRole());
		if( role == null ) throw new RuntimeException("role not found!!!");
		ModelMapper modelMapper = new ModelMapper();
		SecretaireEntity secretaireEntity = modelMapper.map(secretaireDto, SecretaireEntity.class);
		List<UserEntity> secretaires = Collections.singletonList(secretaireEntity);
		role.setUsers(secretaires);
		secretaireEntity.setRole(role);
		secretaireEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(secretaireDto.getPassword()));
		secretaireEntity.setUserID(utils.generatId(32));
		SecretaireEntity newSecretaire = secretaireRepository.save(secretaireEntity);
		SecretaireDto secretaireDt = modelMapper.map(newSecretaire, SecretaireDto.class);
		return secretaireDt;
	}


	@Override
	public SecretaireDto getSecretaire(String id) {
		SecretaireEntity checkSecretaire = secretaireRepository.findByUserId(id);
		if( checkSecretaire == null ) throw new RuntimeException("Secretaire not found!!!");
		ModelMapper modelMapper = new ModelMapper();
		SecretaireDto secretaireDto = modelMapper.map(checkSecretaire, SecretaireDto.class);
		return secretaireDto;
	}


	@Override
	public List<SecretaireDto> getSecretaires(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<SecretaireDto> secretaireDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<SecretaireEntity> secretairePage = secretaireRepository.findAll(pageableRequest);
		List<SecretaireEntity> secretaires = secretairePage.getContent();
		for(SecretaireEntity secretaireEntity: secretaires) {
			ModelMapper modelMapper = new ModelMapper();
			SecretaireDto secretaireDto = modelMapper.map(secretaireEntity, SecretaireDto.class);
			secretaireDtos.add(secretaireDto);
		}
		return secretaireDtos;
	}


	@Override
	public SecretaireDto updateSecretaire(String id, SecretaireDto secretaireDto) {
		SecretaireEntity secretaire = secretaireRepository.findByUserId(id);
		if( secretaire == null ) throw new RuntimeException("Secretaire not found!!!");
		secretaire.setEmail(secretaireDto.getEmail());
		secretaire.setFirstName(secretaireDto.getFirstName());
		secretaire.setLastName(secretaireDto.getLastName());
		secretaire.setSalaireSec(secretaireDto.getSalaireSec());
		SecretaireEntity updatedSec = secretaireRepository.save(secretaire);
		ModelMapper modelMapper = new ModelMapper();
		SecretaireDto secDto = modelMapper.map(updatedSec, SecretaireDto.class);
		return secDto;
	}


	@Override
	public void deleteSecretaire(String id) {
		SecretaireEntity secretaire = secretaireRepository.findByUserId(id);
		if( secretaire == null ) throw new RuntimeException("Secretaire not found!!!");
	    secretaireRepository.delete(secretaire);
	}

}
