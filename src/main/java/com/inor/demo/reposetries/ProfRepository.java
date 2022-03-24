package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.ProfEntity;

@Repository
public interface ProfRepository extends PagingAndSortingRepository<ProfEntity, Long> {

	ProfEntity findByEmail(String email);

	ProfEntity findByUserId(String id);

}
