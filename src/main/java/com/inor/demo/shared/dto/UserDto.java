package com.inor.demo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	
	private static final long serialVersionUID = 1229310354019565425L;
	private Long id;
	private String userId;
	private String FirstName;
	private String LastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatut = false;
	private RoleDto role;
	private List<AbsenceDto> absences;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Boolean isEmailVerificationStatut() {
		return emailVerificationStatut;
	}
	public void setEmailVerificationStatut(Boolean emailVerificationStatut) {
		this.emailVerificationStatut = emailVerificationStatut;
	}
	
	public List<AbsenceDto> getAbsences() {
		return absences;
	}
	public void setAbsences(List<AbsenceDto> absences) {
		this.absences = absences;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	
	
	
	
	
}
