package com.inor.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "niveaux")
public class NiveauEntity implements Serializable {

	
	private static final long serialVersionUID = -2285798619999720639L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String niveauId;
	private String niveau;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "niveaux")
	private Set<MatiereEntity> matieres = new HashSet<>();
	public NiveauEntity(Long id, String niveauId, String niveau) {
		super();
		this.id = id;
		this.niveauId = niveauId;
		this.niveau = niveau;
	}
	public NiveauEntity() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNiveauId() {
		return niveauId;
	}
	public void setNiveauId(String niveauId) {
		this.niveauId = niveauId;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public Set<MatiereEntity> getMatieres() {
		return matieres;
	}
	public void setMatieres(Set<MatiereEntity> matieres) {
		this.matieres = matieres;
	}
	
	
	

	
}
