package com.example.main.models;

import java.util.List;

public class postOrder {
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
	public List<orderDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<orderDetail> detailList) {
		this.detailList = detailList;
	}
	private int user_id;
	private String address;
	private String status;
	private List<orderDetail> detailList;
	
	
}
