package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.RoleEntity;
import com.inor.demo.reposetries.RoleRepository;
import com.inor.demo.sevices.RoleService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.RoleDto;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	Utils utils;
	
	@Override
	public RoleDto createRole(RoleDto roleDto) {
		RoleEntity checkRole = roleRepository.findByRole(roleDto.getRole());
		if(checkRole != null) throw new RuntimeException("Role already exist");
		ModelMapper modelMapper = new ModelMapper();
		RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
		roleEntity.setRoleId(utils.generatId(32));
		RoleEntity createdRole = roleRepository.save(roleEntity);
		RoleDto role  = modelMapper.map(createdRole, RoleDto.class);
		return role;
	}

	@Override
	public RoleDto getRole(String id) {
		RoleEntity role = roleRepository.findByRoleId(id);
		if(role == null) throw new RuntimeException("Role note found!!!");
		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
		return roleDto;
	}

	@Override
	public List<RoleDto> getRoles(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<RoleDto> roleDtos = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<RoleEntity> rolePage = roleRepository.findAll(pageableRequest);
		List<RoleEntity> roles = rolePage.getContent();
		for(RoleEntity roleEntity: roles) {
			ModelMapper modelMapper = new ModelMapper();
			RoleDto roleDto = modelMapper.map(roleEntity, RoleDto.class);
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}

	@Override
	public RoleDto updateRole(String id, RoleDto roleDto) {
		RoleEntity role = roleRepository.findByRoleId(id);
		if(role == null) throw new RuntimeException("Role note found!!!");
		role.setRole(roleDto.getRole());
		RoleEntity updatedRole = roleRepository.save(role);
		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDt = modelMapper.map(updatedRole, RoleDto.class);
		return roleDt;
	}

	@Override
	public void deleteRole(String id) {
		RoleEntity role = roleRepository.findByRoleId(id);
		if(role == null) throw new RuntimeException("Role note found!!!");
		roleRepository.delete(role);
	}

}
