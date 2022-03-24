package com.inor.demo.shared.dto;

import java.util.Date;
import java.util.List;

public class EtudiantDto extends UserDto {

	
	private static final long serialVersionUID = -3866868571915709564L;
	
	
	private String cne;
	private Date date_nais;
	private List<SeanceDto> seances;
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
	public List<SeanceDto> getSeances() {
		return seances;
	}
	public void setSeances(List<SeanceDto> seances) {
		this.seances = seances;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	
	
}
