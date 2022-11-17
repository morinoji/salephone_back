package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class Comment {
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getComment_createdAt() {
		return comment_createdAt;
	}

	public void setComment_createdAt(String comment_createdAt) {
		this.comment_createdAt = comment_createdAt;
	}

	public String getComment_updatedAt() {
		return comment_updatedAt;
	}

	public void setComment_updatedAt(String comment_updatedAt) {
		this.comment_updatedAt = comment_updatedAt;
	}
	private int comment_id;
	private int user_id;
	private int product_id; 
	private String avatar;
	private String fullname;
	private String comment_content;
	private float stars;
	private String comment_createdAt;
	private String comment_updatedAt;
	
	
	public Comment() {

	}
	
	public Comment(int comment_id, int user_id, int product_id, String comment_content, float stars,
			String comment_createdAt, String comment_updatedAt) {

		this.comment_id = comment_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.comment_content = comment_content;
		this.stars = stars;
		this.comment_createdAt = comment_createdAt;
		this.comment_updatedAt = comment_updatedAt;
	}

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	
	
}
