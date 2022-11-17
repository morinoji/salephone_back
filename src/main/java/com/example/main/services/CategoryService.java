package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.models.Category;
import com.example.main.repositories.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository careRepo;

	public CategoryService(CategoryRepository careRepo) {
		super();
		this.careRepo = careRepo;
	}
	
	public List<Category> findAll(){
		return careRepo.findAll();
	}
}
