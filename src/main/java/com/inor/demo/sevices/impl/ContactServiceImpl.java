package com.inor.demo.sevices.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inor.demo.entities.ContactParentEntity;
import com.inor.demo.reposetries.ContactRepository;
import com.inor.demo.sevices.ContactService;
import com.inor.demo.shared.Utils;
import com.inor.demo.shared.dto.ContactDto;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	Utils utils;

	@Override
	public ContactDto createContact(ContactDto contactDto) {
		ContactParentEntity checkContact = contactRepository.findByTelephone(contactDto.getTelephone());
		if(checkContact != null) throw new RuntimeException("concatct already exist!");
		ModelMapper modelMapper = new ModelMapper();
		ContactParentEntity contactParentEntity = modelMapper.map(contactDto, ContactParentEntity.class);
		contactParentEntity.setContactId(utils.generatId(32));
		ContactParentEntity newContact = contactRepository.save(contactParentEntity);
		ContactDto contact = modelMapper.map(newContact, ContactDto.class);
		return contact;
	}

	@Override
	public ContactDto getContact(String id) {
		ContactParentEntity contactParentEntity = contactRepository.findByContactId(id);
		if(contactParentEntity == null) throw new RuntimeException("Contact Not found");
		ModelMapper modelMapper = new ModelMapper();
		ContactDto contactDto = modelMapper.map(contactParentEntity, ContactDto.class);
		return contactDto;
	}

	@Override
	public ContactDto updateContact(String id, ContactDto contactDto) {
		ContactParentEntity contactParentEntity = contactRepository.findByContactId(id);
		if(contactParentEntity == null) throw new RuntimeException("Contact Not found");
		contactParentEntity.setFirstName_P(contactDto.getFirstName_P());
		contactParentEntity.setLastName_P(contactDto.getLastName_P());
		contactParentEntity.setAdresse(contactDto.getAdresse());
		contactParentEntity.setEmail(contactDto.getEmail());
		contactParentEntity.setTelephone(contactDto.getTelephone());
		ContactParentEntity updatedContact = contactRepository.save(contactParentEntity);
		ModelMapper modelMapper = new ModelMapper();
		ContactDto contact = modelMapper.map(updatedContact, ContactDto.class);
		return contact;
	}

	@Override
	public void deletContact(String id) {
		ContactParentEntity contactParentEntity = contactRepository.findByContactId(id);
		if(contactParentEntity == null) throw new RuntimeException("Contact Not found");
		contactRepository.delete(contactParentEntity);
	}

}
