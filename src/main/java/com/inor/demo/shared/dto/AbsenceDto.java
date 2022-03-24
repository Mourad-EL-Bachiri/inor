package com.inor.demo.shared.dto;

import java.util.Date;

public class AbsenceDto {

	private Long id;
	private String absenceId;
	private Date date;
	private String heure;
	private String seance;
	private Boolean justif;
	private UserDto user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public String getAbsenceId() {
		return absenceId;
	}

	public void setAbsenceId(String absenceId) {
		this.absenceId = absenceId;
	}

}
