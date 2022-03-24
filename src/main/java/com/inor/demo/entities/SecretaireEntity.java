package com.inor.demo.entities;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity(name = "secretaire")
@DiscriminatorValue("secretaire")
public class SecretaireEntity extends UserEntity{

	private static final long serialVersionUID = 5310714790964417037L;
	
	@Column(name = "salaire_secretaire")
	double salaireSec;
	
	public double getSalaireSec() {
		return salaireSec;
	}
	public void setSalaireSec(double salaireSec) {
		this.salaireSec = salaireSec;
	}
	
	

}
