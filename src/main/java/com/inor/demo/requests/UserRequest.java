package com.inor.demo.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserRequest {
	
	@NotBlank(message= "Ce champ ne doit pas  etre null !!")
	@Size(min = 2, message ="Ce champ doit avoir au moins 2 caractères !")
	private String FirstName;
	
	@NotBlank(message= "Ce champ ne doit pas  etre null !!")
	@Size(min = 2)
	private String LastName;
	
	@NotNull(message= "Ce champ ne doit pas  etre null !!")
	@Email(message= "Ce champ doit Respecter format Email !!")
	private String email;
	
	@NotNull(message= "Ce champ ne doit pas  etre null !!")
	@Size(min = 6, message ="Ce champ doit avoir au moins 6 caractères !")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Password (UpperCase, LowerCase and Number)")
	private String password;
	
	private RoleRequest role;
	
	private List<AbsenceRequest> absences;
	 
	
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
	public List<AbsenceRequest> getAbsences() {
		return absences;
	}
	public void setAbsences(List<AbsenceRequest> absences) {
		this.absences = absences;
	}
	public RoleRequest getRole() {
		return role;
	}
	public void setRole(RoleRequest role) {
		this.role = role;
	}
	
	
	

}
