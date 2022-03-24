package com.inor.demo.requests;

import java.util.Date;
import java.util.List;

public class SeanceRequest {
	
	
    private Date date_seance;
    private String heure;
    private MatiereRequest matiere;
    private List<EtudiantRequest> etudiants;
    
	public Date getDate_seance() {
		return date_seance;
	}
	public void setDate_seance(Date date_seance) {
		this.date_seance = date_seance;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public MatiereRequest getMatiere() {
		return matiere;
	}
	public void setMatiere(MatiereRequest matiere) {
		this.matiere = matiere;
	}
	public List<EtudiantRequest> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<EtudiantRequest> etudiants) {
		this.etudiants = etudiants;
	}
    
    

}
