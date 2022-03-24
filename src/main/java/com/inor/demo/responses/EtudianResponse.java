package com.inor.demo.responses;

import java.util.Date;
import java.util.List;

public class EtudianResponse  extends UserResponse{

	private String cne;
	private Date date_nais;
	private List<SeanceResponse> seances;
	private ContactResponse contact;
	
	
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
	public List<SeanceResponse> getSeances() {
		return seances;
	}
	public void setSeances(List<SeanceResponse> seances) {
		this.seances = seances;
	}
	public ContactResponse getContact() {
		return contact;
	}
	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}
	
	
}
