package com.inor.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="contact_parents")
public class ContactParentEntity  implements Serializable{
	
	private static final long serialVersionUID = 8418965695590960537L;
	@Id
	@GeneratedValue
	private Long id;
	private String contactId;
	@Column(name = "firstNameParent")
	private String firstName_P;
	@Column(name = "lastNameParent")
	private String lastName_P;
	private String adresse;
	private String telephone;
	private String email;
	
	@OneToOne
	private EtudiantEntity etudiant;
	
	
	public ContactParentEntity() {
		super();
		
	}


	public ContactParentEntity(Long id, String contactId, String firstName_P, String lastName_P, String adresse,
			String telephone, String email) {
		super();
		this.id = id;
		this.contactId = contactId;
		this.firstName_P = firstName_P;
		this.lastName_P = lastName_P;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
	}
	
	
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


	public EtudiantEntity getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(EtudiantEntity etudiant) {
		this.etudiant = etudiant;
	}
	
	

}
