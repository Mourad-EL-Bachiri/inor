package com.inor.demo.responses;


public class UserResponse {
	
	private String userId;
	private String FirstName;
	private String LastName;
	private String email;
	private RoleResponse role;
	
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
	public RoleResponse getRole() {
		return role;
	}
	public void setRole(RoleResponse role) {
		this.role = role;
	}
	
	

}
