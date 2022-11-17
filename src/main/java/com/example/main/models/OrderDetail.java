package com.example.main.models;

public class OrderDetail {
	public int getOrderdetail_id() {
		return orderdetail_id;
	}
	public void setOrderdetail_id(int orderdetail_id) {
		this.orderdetail_id = orderdetail_id;
	}
	public int order_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	public String getOrderdetail_created_at() {
		return orderdetail_created_at;
	}
	public void setOrderdetail_created_at(String orderdetail_created_at) {
		this.orderdetail_created_at = orderdetail_created_at;
	}
	public String getOrderdetail_updated_at() {
		return orderdetail_updated_at;
	}
	public void setOrderdetail_updated_at(String orderdetail_updated_at) {
		this.orderdetail_updated_at = orderdetail_updated_at;
	}
	private int orderdetail_id;
	private int order_id;
	private int product_id;
	private int quantity;
	private String orderdetail_created_at;
	private String orderdetail_updated_at;
	
	
	public OrderDetail() {
	}
	public OrderDetail(int orderdetail_id, int order_id, int product_id, int quantity, String orderdetail_created_at,
			String orderdetail_updated_at) {
		this.orderdetail_id = orderdetail_id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.orderdetail_created_at = orderdetail_created_at;
		this.orderdetail_updated_at = orderdetail_updated_at;
	}
	
	
}
