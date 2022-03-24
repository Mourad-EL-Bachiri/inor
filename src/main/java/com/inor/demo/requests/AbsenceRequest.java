package com.inor.demo.requests;

import java.util.Date;

public class AbsenceRequest {
	
	
	private Date date;
	private String heure;
	private String seance;
	private Boolean justif = false;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getSeance() {
		return seance;
	}
	public void setSeance(String seance) {
		this.seance = seance;
	}
	public Boolean getJustif() {
		return justif;
	}
	public void setJustif(Boolean justif) {
		this.justif = justif;
	}
	
	
	
	
	

}
