package com.inor.demo.reposetries;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inor.demo.entities.UserEntity;

@Repository
public interface UserReposetry extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);
}
