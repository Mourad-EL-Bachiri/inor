package com.inor.demo.responses;

import java.util.List;


public class MatiereResponse {

	private String matiereId;
	private String nameMat;
	private Double prix;
	private List<NiveauResponse> niveaux;
	private ProfResponse prof;
 	
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
	public List<NiveauResponse> getNiveaux() {
		return niveaux;
	}
	public void setNiveaux(List<NiveauResponse> niveaux) {
		this.niveaux = niveaux;
	}
	public ProfResponse getProf() {
		return prof;
	}
	public void setProf(ProfResponse prof) {
		this.prof = prof;
	}
	
	
}
