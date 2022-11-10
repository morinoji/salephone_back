package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.models.category;
import com.example.main.repositories.categoryRepository;

@Service
public class categoryService {
	private categoryRepository careRepo;

	public categoryService(categoryRepository careRepo) {
		super();
		this.careRepo = careRepo;
	}
	
	public List<category> findAll(){
		return careRepo.findAll();
	}
}
