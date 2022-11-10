package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class roleUser {
	private int roleuser_id;
	private int user_id;
	private int role_id;
	private String createdAt;
	private String updatedAt;
	public int getRoleuser_id() {
		return roleuser_id;
	}
	public void setRoleuser_id(int roleuser_id) {
		this.roleuser_id = roleuser_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
