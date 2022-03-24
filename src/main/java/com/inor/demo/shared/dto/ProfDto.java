package com.inor.demo.shared.dto;

import java.util.List;

public class ProfDto extends UserDto {

	
	private static final long serialVersionUID = 6847185207482381150L;
	
	private double salaireProf;
	private List<MatiereDto> matieres;

	public double getSalaireProf() {
		return salaireProf;
	}

	public void setSalaireProf(double salaireProf) {
		this.salaireProf = salaireProf;
	}

	public List<MatiereDto> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<MatiereDto> matieres) {
		this.matieres = matieres;
	}
	

}
