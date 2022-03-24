package com.inor.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="matieres")
public class MatiereEntity implements Serializable {


	private static final long serialVersionUID = 1770860054317566663L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String matiereId;
	private String nameMat;
	private Double prix;
	
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	private List<SeanceEntity> seances;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "prof_id")
	private ProfEntity prof;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Niv_Mat", joinColumns = {@JoinColumn(name = "matiere_id")}, inverseJoinColumns = {@JoinColumn(name="niveau_id")})
	private Set<NiveauEntity> niveaux = new HashSet<>();
	
	public MatiereEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MatiereEntity(Long id, String matiereId, String nameMat, Double prix, Set<NiveauEntity> niveaux) {
		super();
		this.id = id;
		this.matiereId = matiereId;
		this.nameMat = nameMat;
		this.prix = prix;
		this.niveaux = niveaux;
	}

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

	public Set<NiveauEntity> getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(Set<NiveauEntity> niveaux) {
		this.niveaux = niveaux;
	}

	public List<SeanceEntity> getSeances() {
		return seances;
	}

	public void setSeances(List<SeanceEntity> seances) {
		this.seances = seances;
	}

	public ProfEntity getProf() {
		return prof;
	}

	public void setProf(ProfEntity prof) {
		this.prof = prof;
	}
	
	
	

}
