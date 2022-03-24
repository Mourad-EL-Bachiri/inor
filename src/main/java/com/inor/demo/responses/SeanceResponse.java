package com.inor.demo.responses;

import java.util.Date;

public class SeanceResponse {

	
	private String seanceId;
    private Date date_seance;
    private String heure;
    private MatiereResponse matiere;
    
	public String getSeanceId() {
		return seanceId;
	}
	public void setSeanceId(String seanceId) {
		this.seanceId = seanceId;
	}
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
	public MatiereResponse getMatiere() {
		return matiere;
	}
	public void setMatiere(MatiereResponse matiere) {
		this.matiere = matiere;
	}
    
    
}
