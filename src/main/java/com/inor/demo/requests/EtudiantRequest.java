package com.inor.demo.requests;

import java.util.Date;

import com.inor.demo.shared.dto.ContactDto;

public class EtudiantRequest extends UserRequest {

	private String cne;
	private Date date_nais;
	private ContactDto contact;
	
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public Date getDate_nais() {
		return date_nais;
	}
	public void setDate_nais(Date date_nais) {
		this.date_nais = date_nais;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	
	
}
