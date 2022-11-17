package com.example.main.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class User implements UserDetails {
	
	
	public String getUser_fullname() {
		return user_fullname;
	}
	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	@Override
	public String toString() {
		return "user [user_id=" + user_id + ", username=" + user_name + ", user_password=" + user_password
				+ ", user_email=" + user_email + ", user_phone_number=" + user_phone_number + ", user_date_of_birth="
				+ user_date_of_birth + ", user_address=" + user_address + ", fullname=" + user_fullname + ", user_avatar="
				+ user_avatar + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", roles=" + roles + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int user_id;
	
	@NotBlank
	private String user_name;
	
	@NotBlank 
	@Size(min=6, max=12 )
	private String user_password;
	
	@NotBlank
	private String user_email;
	
	@NotBlank
	@Size(min=10,max=12)
	private String user_phone_number;
	
	@NotNull
	private String user_date_of_birth;
	@NotBlank
	private String user_address;
	
	@NotBlank
	private String user_fullname;
	private String user_avatar;
	private String createdAt;
	private String updatedAt;
	private GrantedAuthority roles;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public GrantedAuthority getRoles() {
		return roles;
	}
	public void setRoles(GrantedAuthority roles) {
		this.roles = roles;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_date_of_birth() {
		return user_date_of_birth;
	}
	public void setUser_date_of_birth(String user_date_of_birth) {
		this.user_date_of_birth = user_date_of_birth;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(this.getRoles());
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user_password;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUser_name();
	}
	
	
	
}
