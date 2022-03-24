package com.inor.demo.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "professeurs")
@DiscriminatorValue("professeur")
public class ProfEntity extends UserEntity {

	
	private static final long serialVersionUID = 3340022460666254243L;
	
	@Column(name = "Salaire_professeur")
	private double salaireProf;
	
	@OneToMany(mappedBy = "prof", cascade = CascadeType.PERSIST)
	private List<MatiereEntity> matieres;
	
	
	public ProfEntity() {
		super();
		
	}
	public ProfEntity( double salaireProf) {
		super();
		this.salaireProf = salaireProf;
	}
	public double getSalaireProf() {
		return salaireProf;
	}
	public void setSalaireProf(double salaireProf) {
		this.salaireProf = salaireProf;
	}
	public List<MatiereEntity> getMatieres() {
		return matieres;
	}
	public void setMatieres(List<MatiereEntity> matieres) {
		this.matieres = matieres;
	}
	
	
	

}
