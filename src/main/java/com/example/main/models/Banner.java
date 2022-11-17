package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class Banner {
	public int getSlider_id() {
		return slider_id;
	}
	public void setSlider_id(int slider_id) {
		this.slider_id = slider_id;
	}
	public String getSlider_name() {
		return slider_name;
	}
	public void setSlider_name(String slider_name) {
		this.slider_name = slider_name;
	}
	public String getSlider_link() {
		return slider_link;
	}
	public void setSlider_link(String slider_link) {
		this.slider_link = slider_link;
	}
	public String getSlider_image() {
		return slider_image;
	}
	public void setSlider_image(String slider_image) {
		this.slider_image = slider_image;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	private int slider_id;
	private String slider_name;
	private String slider_link;
	private String slider_image;
	private String created_at;
	private String updated_at;
	
	
	
	
}
