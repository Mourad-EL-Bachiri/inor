package com.inor.demo.reposetries;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.ContactParentEntity;


@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactParentEntity, Long>{

	ContactParentEntity findByTelephone(String telephone);
	ContactParentEntity findByContactId(String id);
	
}
