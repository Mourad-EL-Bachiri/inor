package com.inor.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "roles" )
public class RoleEntity implements Serializable{


	private static final long serialVersionUID = 7062103788866095925L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleId;
	private String role;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserEntity> users;
	
	public RoleEntity(Long id, String roleId, String role) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.role = role;
	}
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
	
}
