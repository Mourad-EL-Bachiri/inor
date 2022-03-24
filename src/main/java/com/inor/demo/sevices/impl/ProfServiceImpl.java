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

import com.inor.demo.entities.ProfEntity;
import com.inor.demo.entities.RoleEntity;
import com.inor.demo.entities.UserEntity;
import com.inor.demo.reposetries.ProfRepository;
import com.inor.demo.reposetries.RoleRepository;
import com.inor.demo.sevices.ProfService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.ProfDto;
import com.inor.demo.shared.dto.RoleDto;

@Service
public class ProfServiceImpl implements ProfService {

	@Autowired
	ProfRepository profRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder brBCryptPasswordEncoder;

	@Override
	public ProfDto createProf(ProfDto profDto) {
		ProfEntity prof = profRepository.findByEmail(profDto.getEmail());
		if(prof != null) throw new RuntimeException("Prof Already Exist");
		RoleDto roleDto = profDto.getRole();
		RoleEntity role = roleRepository.findByRole(roleDto.getRole());
		if( role == null ) throw new RuntimeException("role makainch!!!");
		
		ModelMapper modelMapper = new ModelMapper();
		ProfEntity profEntity = modelMapper.map(profDto, ProfEntity.class);
		List<UserEntity> profs = Collections.singletonList(profEntity);
		role.setUsers(profs);
		profEntity.setRole(role);
		profEntity.setEncryptedPassword(brBCryptPasswordEncoder.encode(profDto.getPassword()));
		profEntity.setUserId(utils.generatId(32));
		ProfEntity createdProf = profRepository.save(profEntity);
		ProfDto profDt = modelMapper.map(createdProf, ProfDto.class);
		return profDt;
	}

	@Override
	public ProfDto getProf(String id) {
		ProfEntity prof = profRepository.findByUserId(id);
		if(prof == null) throw new RuntimeException("Prof not found");
		ModelMapper modelMapper = new ModelMapper();
		ProfDto profDto = modelMapper.map(prof, ProfDto.class);
		return profDto;
	}

	@Override
	public List<ProfDto> getAllprofs(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<ProfDto> profDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ProfEntity> profPage = profRepository.findAll(pageableRequest);
		List<ProfEntity> profs = profPage.getContent();
		for(ProfEntity profEntity: profs) {
			ModelMapper modelMapper = new ModelMapper();
			ProfDto profDto = modelMapper.map(profEntity, ProfDto.class);
			profDtos.add(profDto);
		}
		return profDtos;
	}

	@Override
	public ProfDto updateProf(String id, ProfDto profDto) {
		ProfEntity prof = profRepository.findByUserId(id);
		if(prof == null) throw new RuntimeException("Prof not found");
		prof.setEmail(profDto.getEmail());
		prof.setFirstName(profDto.getFirstName());
		prof.setLastName(profDto.getLastName());
		prof.setSalaireProf(profDto.getSalaireProf());
		ProfEntity updatedProf = profRepository.save(prof);
		ModelMapper modelMapper = new ModelMapper();
		ProfDto profDt= modelMapper.map(updatedProf, ProfDto.class);
		return profDt;
	}

	@Override
	public void deleteProf(String id) {
		ProfEntity prof = profRepository.findByUserId(id);
		if(prof == null) throw new RuntimeException("Prof not found");
		profRepository.delete(prof);
	}
	
}
