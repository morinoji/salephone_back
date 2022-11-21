package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.models.Category;
import com.example.main.repositories.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository cateRepo;

	public CategoryService(CategoryRepository cateRepo) {
		super();
		this.cateRepo = cateRepo;
	}
	
	public List<Category> findAll(){
		return cateRepo.findAll();
	}
	
	public String addNewCategory(String category_name) {
		return cateRepo.addNewCategory(category_name);
	}
	
	public String editCategory(int category_id,String category_name) {
		return cateRepo.editCategory(category_id,category_name);
	}
	
	public String deleteCategory(int category_id) {
		return cateRepo.deleteCategory(category_id);
	}
}
