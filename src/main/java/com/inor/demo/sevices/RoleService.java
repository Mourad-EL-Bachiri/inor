package com.inor.demo.sevices;

import java.util.List;

import com.inor.demo.shared.dto.RoleDto;

public interface RoleService {

	RoleDto createRole(RoleDto roleDto);

	RoleDto getRole(String id);

	List<RoleDto> getRoles(int page, int limit);

	RoleDto updateRole(String id, RoleDto roleDto);

	void deleteRole(String id);

}
