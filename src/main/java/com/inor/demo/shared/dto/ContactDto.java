package com.inor.demo.shared.dto;

import java.io.Serializable;

public class ContactDto implements Serializable {
	
	
	
	private static final long serialVersionUID = 8233831713537421574L;
	private Long id;
	private String contactId;
	private String firstName_P;
	private String lastName_P;
	private String adresse;
	private String telephone;
	private String email;
	private EtudiantDto etudiant;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getFirstName_P() {
		return firstName_P;
	}
	public void setFirstName_P(String firstName_P) {
		this.firstName_P = firstName_P;
	}
	public String getLastName_P() {
		return lastName_P;
	}
	public void setLastName_P(String lastName_P) {
		this.lastName_P = lastName_P;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EtudiantDto getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}
	

}
