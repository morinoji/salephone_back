package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class cartDetail {
	private int cartdetail_id;
	private int cart_id;
	private int product_id;
	private int quantity;
	private String createdAt;
	private String updatedAt;
	
	
	
	public cartDetail() {

	}
	public cartDetail(int cartdetail_id, int cart_id, int product_id, int quantity) {
		this.cartdetail_id = cartdetail_id;
		this.cart_id = cart_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	public int getCartdetail_id() {
		return cartdetail_id;
	}
	public void setCartdetail_id(int cartdetail_id) {
		this.cartdetail_id = cartdetail_id;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
