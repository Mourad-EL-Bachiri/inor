package com.inor.demo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class MatiereDto implements Serializable {

	private static final long serialVersionUID = 4617760177564351327L;
	
	private Long id;
	private String matiereId;
	private String nameMat;
	private Double prix;
	private List<SeanceDto> seances;
	private List<NiveauDto> niveaux;
	private ProfDto prof;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatiereId() {
		return matiereId;
	}
	public void setMatiereId(String matiereId) {
		this.matiereId = matiereId;
	}
	public String getNameMat() {
		return nameMat;
	}
	public void setNameMat(String nameMat) {
		this.nameMat = nameMat;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public List<NiveauDto> getNiveaux() {
		return niveaux;
	}
	public void setNiveaux(List<NiveauDto> niveaux) {
		this.niveaux = niveaux;
	}
	public List<SeanceDto> getSeances() {
		return seances;
	}
	public void setSeances(List<SeanceDto> seances) {
		this.seances = seances;
	}
	public ProfDto getProf() {
		return prof;
	}
	public void setProf(ProfDto prof) {
		this.prof = prof;
	}
	
	
	

}
