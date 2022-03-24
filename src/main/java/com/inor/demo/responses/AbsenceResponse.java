package com.inor.demo.responses;

import java.util.Date;

public class AbsenceResponse {

	private String absenceId;
	private Date date;
	private String heure;
	private String seance;
	private Boolean justif;
	public String getAbsenceId() {
		return absenceId;
	}
	public void setAbsenceId(String absenceId) {
		this.absenceId = absenceId;
	}
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
