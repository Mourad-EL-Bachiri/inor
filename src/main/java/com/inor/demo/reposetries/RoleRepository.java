package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.RoleEntity;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {

	RoleEntity findByRole(String role);

	RoleEntity findByRoleId(String id);

}
