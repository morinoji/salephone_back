package com.example.main.models;

import org.springframework.stereotype.Component;

@Component
public class productDetail {
	
	private int detail_id;
	private String model_name;
	private String detail_screen;
	private String detail_os;
	private String detail_behindcam;
	private String detail_frontcam;
	private String detail_chip;
	private String detail_ram;
	private String detail_internalmem;
	private String detail_sim;
	private String detail_pin;
	private String detail_createdAt;
	private String detail_updatedAt;
	
	
	public productDetail() {

	}
	public productDetail(int detail_id, String model_name, String detail_screen, String detail_os,
			String detail_behindcam, String detail_frontcam, String detail_chip, String detail_ram,
			String detail_internalmem, String detail_sim, String detail_pin, String detail_createdAt,
			String detail_updatedAt) {

		this.detail_id = detail_id;
		this.model_name = model_name;
		this.detail_screen = detail_screen;
		this.detail_os = detail_os;
		this.detail_behindcam = detail_behindcam;
		this.detail_frontcam = detail_frontcam;
		this.detail_chip = detail_chip;
		this.detail_ram = detail_ram;
		this.detail_internalmem = detail_internalmem;
		this.detail_sim = detail_sim;
		this.detail_pin = detail_pin;
		this.detail_createdAt = detail_createdAt;
		this.detail_updatedAt = detail_updatedAt;
	}
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getDetail_screen() {
		return detail_screen;
	}
	public void setDetail_screen(String detail_screen) {
		this.detail_screen = detail_screen;
	}
	public String getDetail_os() {
		return detail_os;
	}
	public void setDetail_os(String detail_os) {
		this.detail_os = detail_os;
	}
	public String getDetail_behindcam() {
		return detail_behindcam;
	}
	public void setDetail_behindcam(String detail_behindcam) {
		this.detail_behindcam = detail_behindcam;
	}
	public String getDetail_frontcam() {
		return detail_frontcam;
	}
	public void setDetail_frontcam(String detail_frontcam) {
		this.detail_frontcam = detail_frontcam;
	}
	public String getDetail_chip() {
		return detail_chip;
	}
	public void setDetail_chip(String detail_chip) {
		this.detail_chip = detail_chip;
	}
	public String getDetail_ram() {
		return detail_ram;
	}
	public void setDetail_ram(String detail_ram) {
		this.detail_ram = detail_ram;
	}
	public String getDetail_internalmem() {
		return detail_internalmem;
	}
	public void setDetail_internalmem(String detail_internalmem) {
		this.detail_internalmem = detail_internalmem;
	}
	public String getDetail_sim() {
		return detail_sim;
	}
	public void setDetail_sim(String detail_sim) {
		this.detail_sim = detail_sim;
	}
	public String getDetail_pin() {
		return detail_pin;
	}
	public void setDetail_pin(String detail_pin) {
		this.detail_pin = detail_pin;
	}
	
	public String getDetail_createdAt() {
		return detail_createdAt;
	}
	public void setDetail_createdAt(String detail_createdAt) {
		this.detail_createdAt = detail_createdAt;
	}
	public String getDetail_updatedAt() {
		return detail_updatedAt;
	}
	public void setDetail_updatedAt(String detail_updatedAt) {
		this.detail_updatedAt = detail_updatedAt;
	}
	
}
