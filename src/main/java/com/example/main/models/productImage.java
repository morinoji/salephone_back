package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class productImage {
	
	private int image_id;
	private String image_name;
	private int product_id;
	private String image_createdAt;
	private String image_updatedAt;
	
	
	
	public productImage() {

	}
	public productImage(int image_id, String image_name, int product_id, String image_createdAt,
			String image_updatedAt) {
		super();
		this.image_id = image_id;
		this.image_name = image_name;
		this.product_id = product_id;
		this.image_createdAt = image_createdAt;
		this.image_updatedAt = image_updatedAt;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getImage_createdAt() {
		return image_createdAt;
	}
	public void setImage_createdAt(String image_createdAt) {
		this.image_createdAt = image_createdAt;
	}
	public String getImage_updatedAt() {
		return image_updatedAt;
	}
	public void setImage_updatedAt(String image_updatedAt) {
		this.image_updatedAt = image_updatedAt;
	}
}
