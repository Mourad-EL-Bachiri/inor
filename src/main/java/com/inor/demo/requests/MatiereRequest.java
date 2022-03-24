package com.inor.demo.requests;

import java.util.List;

public class MatiereRequest {

	private String nameMat;
	private Double prix;
	private List<NiveauRequest> niveaux;
	private ProfRequest prof;
	
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
	public List<NiveauRequest> getNiveaux() {
		return niveaux;
	}
	public void setNiveaux(List<NiveauRequest> niveaux) {
		this.niveaux = niveaux;
	}
	public ProfRequest getProf() {
		return prof;
	}
	public void setProf(ProfRequest prof) {
		this.prof = prof;
	}
	
	
}
