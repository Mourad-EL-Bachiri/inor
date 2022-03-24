package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.SecretaireEntity;

@Repository
public interface SecretaireRepository extends PagingAndSortingRepository<SecretaireEntity, Long> {

	SecretaireEntity findByEmail(String email);

	SecretaireEntity findByUserId(String id);

}
