package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.MatiereEntity;

@Repository
public interface MatiereRepository extends PagingAndSortingRepository<MatiereEntity, Long> {
	
	MatiereEntity findByNameMat(String nameMat);

	MatiereEntity findByMatiereId(String matiereId);
	
}
