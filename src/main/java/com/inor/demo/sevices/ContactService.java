package com.inor.demo.sevices;

import com.inor.demo.shared.dto.ContactDto;

public interface ContactService {
	
	ContactDto createContact(ContactDto contactDto);
	ContactDto getContact(String id);
	ContactDto updateContact(String id, ContactDto contactDto);
	void deletContact(String id);
	

}
