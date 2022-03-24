package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.NiveauEntity;

@Repository
public interface NiveauRepository extends PagingAndSortingRepository<NiveauEntity, Long>{

	NiveauEntity findByNiveau(String niveau);

	NiveauEntity findByNiveauId(String id);
	

}
