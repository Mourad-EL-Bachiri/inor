package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.SeanceEntity;

@Repository
public interface SeanceRepository extends PagingAndSortingRepository<SeanceEntity, Long>{

	SeanceEntity findBySeanceId(String id);

}
