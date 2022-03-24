package com.inor.demo.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SeanceDto implements Serializable{

	private static final long serialVersionUID = -599181422462802906L;
    
	private Long id;
	private String seanceId;
    private Date date_seance;
    private String heure;
    private MatiereDto matiere;
    private List<EtudiantDto> etudiants;
    
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MatiereDto getMatiere() {
		return matiere;
	}
	public void setMatiere(MatiereDto matiere) {
		this.matiere = matiere;
	}
	public List<EtudiantDto> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}
    
    
}
