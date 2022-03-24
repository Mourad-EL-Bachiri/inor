package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.AbsenceEntity;

@Repository
public interface AbsenceRepository extends PagingAndSortingRepository<AbsenceEntity, Long> {

	AbsenceEntity findByAbsenceId(String id);

	
}
