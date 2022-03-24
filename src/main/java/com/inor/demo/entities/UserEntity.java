package com.inor.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = -1933384456047462185L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, length = 50)
	private String FirstName;
	
	@Column(nullable = false, length = 50)
	private String LastName;
	
	@Column(nullable = false, length = 150, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column(nullable = true)
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatut = false;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private RoleEntity role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AbsenceEntity> absences;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserID(String userID) {
		userId = userID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatut() {
		return emailVerificationStatut;
	}

	public void setEmailVerificationStatut(Boolean emailVerificationStatut) {
		this.emailVerificationStatut = emailVerificationStatut;
	}
	
	public List<AbsenceEntity> getAbsences() {
		return absences;
	}

	public void setAbsences(List<AbsenceEntity> absences) {
		this.absences = absences;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	
}
