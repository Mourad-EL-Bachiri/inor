package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.EtudiantEntity;

@Repository
public interface EtudiantRepository extends PagingAndSortingRepository<EtudiantEntity, Long> {

	EtudiantEntity findByCne(String cne);

	EtudiantEntity findByUserId(String id);

	
}
