package com.example.main.models;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Cart {
	
	
	private int cart_id;
	private int user_id;
	private String createdAt;
	private String updatedAt;
	private List<Map<String,Object>> listProducts;
	
	public List<Map<String, Object>> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<Map<String, Object>> listProducts) {
		this.listProducts = listProducts;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
