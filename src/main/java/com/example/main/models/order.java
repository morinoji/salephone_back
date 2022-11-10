package com.example.main.models;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class order {
	public List<Map<String, Object>> getListProd() {
		return listProd;
	}
	public void setListProd(List<Map<String, Object>> listProd) {
		this.listProd = listProd;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrder_created_at() {
		return order_created_at;
	}
	public void setOrder_created_at(String order_created_at) {
		this.order_created_at = order_created_at;
	}
	public String getOrder_updated_at() {
		return order_updated_at;
	}
	public void setOrder_updated_at(String order_updated_at) {
		this.order_updated_at = order_updated_at;
	}
	@NotNull
	private Integer order_id;
	@NotNull
	private Integer user_id;
	@NotBlank
	private String address;
	@NotBlank
	private String status;
	private String order_created_at;
	private String order_updated_at;
	private List<Map<String, Object>> listProd;
	
	
}
