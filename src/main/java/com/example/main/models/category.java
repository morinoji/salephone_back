package com.example.main.models;



import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;




//@Entity
//@Table(name="categories")
//public class category {
//	@Id
//	@Column(name="category_id")
//	private int id;
//	
//	@Column(name="category_name")
//	private String categoryName;
//	
//	@Column(name="parent_id")
//	private int parentId;
//	
//	@Column(name="created_at")
//	private String createdAt;
//	
//	@Column(name="updated_at")
//	private String updatedAt;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}
//
//	public int getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(int parentId) {
//		this.parentId = parentId;
//	}
//
//	public String getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public String getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(String updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//	
//	
//}
@Component
public class category {
	
	private int categoryId;

	private String categoryName;
	

	private int parentId;
	

	private String createdAt;
	

	private String updatedAt;


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
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
