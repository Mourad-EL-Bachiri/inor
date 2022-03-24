package com.inor.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "etudiants")
@DiscriminatorValue("etudiant")
public class EtudiantEntity extends UserEntity {


	private static final long serialVersionUID = -8861599949941544769L;
	
	private String cne;
	@Column(name = "date_naissance")
	private Date date_nais;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "etudiants")
	private List<SeanceEntity> seances;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "etudiant")
	private ContactParentEntity contact;

	public EtudiantEntity(int id, String cne, Date date_nais) {
		super();
		this.cne = cne;
		this.date_nais = date_nais;
	}

	public EtudiantEntity() {
		super();
	}


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

	public List<SeanceEntity> getSeances() {
		return seances;
	}

	public void setSeances(List<SeanceEntity> seances) {
		this.seances = seances;
	}

	public ContactParentEntity getContact() {
		return contact;
	}

	public void setContact(ContactParentEntity contact) {
		this.contact = contact;
	}
	
	
}
