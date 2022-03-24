package com.inor.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="absences")
public class AbsenceEntity implements Serializable {

	
	private static final long serialVersionUID = 5429987295175810509L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = true)
	private String absenceId;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(length = 15, nullable = false)
	private String heure;
	
	@Column(length = 25, nullable = false)
	private String seance;
	
	private Boolean justif;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
