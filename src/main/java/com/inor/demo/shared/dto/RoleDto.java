package com.inor.demo.shared.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

	
	private static final long serialVersionUID = -701942878633323350L;
	
	String roleId;
	String role;
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
	
	


}
