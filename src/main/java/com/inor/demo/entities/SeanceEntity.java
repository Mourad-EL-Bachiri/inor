package com.inor.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity(name="seances")
public class SeanceEntity implements Serializable{
	
	private static final long serialVersionUID = -2623338644304615044L;
	@Id
	@GeneratedValue
	private Long id;
	private String seanceId;
    private Date date_seance;
    private String heure;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "matiere_id")
    MatiereEntity matiere;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Seance_Etudiant", joinColumns = {@JoinColumn(name = "seance_id")}, inverseJoinColumns = {@JoinColumn(name = "etudiant_id")})
    List<EtudiantEntity> etudiants;
    
	public SeanceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SeanceEntity(Long id, String seanceId, Date date_seance, String heure) {
		super();
		this.id = id;
		this.seanceId = seanceId;
		this.date_seance = date_seance;
		this.heure = heure;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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


	public MatiereEntity getMatiere() {
		return matiere;
	}


	public void setMatiere(MatiereEntity matiere) {
		this.matiere = matiere;
	}


	public List<EtudiantEntity> getEtudiants() {
		return etudiants;
	}


	public void setEtudiants(List<EtudiantEntity> etudiants) {
		this.etudiants = etudiants;
	}
    
	
    
	

}
