package com.inor.demo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class NiveauDto implements Serializable {

	
	private static final long serialVersionUID = 7103942841932512185L;

	private Long id;
	private String niveauId;
	private String niveau;
	private List<MatiereDto> matieres;
	
	
	
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
	public List<MatiereDto> getMatieres() {
		return matieres;
	}
	public void setMatieres(List<MatiereDto> matieres) {
		this.matieres = matieres;
	}
	
	
}
