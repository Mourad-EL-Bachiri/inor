package com.inor.demo.sevices.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.RoleEntity;
import com.inor.demo.entities.UserEntity;
import com.inor.demo.reposetries.RoleRepository;
import com.inor.demo.reposetries.UserReposetry;
import com.inor.demo.sevices.UserService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.RoleDto;
import com.inor.demo.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	

	@Autowired
	UserReposetry userReposetry;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utils util;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity checkUser = userReposetry.findByEmail(user.getEmail());
		if( checkUser != null ) throw new RuntimeException("User Already Exists!!!");
		
		
		/* for (int i = 0; i < user.getAbsences().size() ; i++) {
			AbsenceDto absences = user.getAbsences().get(i);
			absences.setUser(user);
			absences.setAbsenceId(util.generatId(32));
			user.getAbsences().set(i, absences);
		} */
		RoleDto roleDto = user.getRole();
		RoleEntity role = roleRepository.findByRole(roleDto.getRole());
		if( role == null ) throw new RuntimeException("role makainch!!!");
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		List<UserEntity> users = Collections.singletonList(userEntity);
		role.setUsers(users);
		userEntity.setRole(role);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserID(util.generatId(32));
		UserEntity newUser = userReposetry.save(userEntity);
		UserDto userDto = modelMapper.map(newUser, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity= userReposetry.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
		
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userReposetry.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userReposetry.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userReposetry.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity userUpdated = userReposetry.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);
		return user;
	}

	@Override
	public void deletUser(String userId) {
		UserEntity userEntity = userReposetry.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		userReposetry.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		if( page > 0 ) page -= 1;
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage = userReposetry.findAll(pageableRequest);
		
		List<UserEntity> users = userPage.getContent();
		for(UserEntity userEntity: users) {
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			usersDto.add(user);
		}
		
		return usersDto;
	}
}
