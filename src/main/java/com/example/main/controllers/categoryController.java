package com.example.main.controllers;




import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.successTemplate;
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.category;
import com.example.main.models.user;
import com.example.main.repositories.categoryRepository;
import com.example.main.repositories.userRepository;
import com.example.main.services.categoryService;

@RestController
@Validated
public class categoryController {

	private categoryService cateSv;
	private userRepository userRepo;
	
	
public categoryController(categoryService cateSv, userRepository userRepo) {
		this.cateSv = cateSv;
		this.userRepo = userRepo;
	}


//	@RequestMapping(value="/addCategory", method = RequestMethod.POST)
//	public String getCategory(@ModelAttribute("categoryName") String cateName, @ModelAttribute("parentId") int parentId){
//		try {
//			cateRepo.insert(cateName, parentId);
//		} catch (Exception e) {
//			return "asdasda";
//		}
//		return "successful";
//	}
//	
	@RequestMapping(value="/findAllCategories")
	public ResponseObject getCategory(){
		return new ResponseObject(200, "Thành công!", cateSv.findAll());
	}
	
	
//	@RequestMapping(value="/all")
//	public user getUser(){
//		return userRepo.findByUserName("morinoji");
//	}
	
//	@RequestMapping(value="/addCategory", method = RequestMethod.POST)
//	public ResponseEntity<Object> addCategory(@NotBlank @Size(min=5,max=10) @ModelAttribute("categoryName") String cateName,@Min(value=0) @ModelAttribute("parentId") int parentId, BindingResult result){
//		return new ResponseEntity<Object>(sc.success(200, "Thêm category thành công!", "success", "/addCategory"), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/updateCategory", method = RequestMethod.POST)
//	public ResponseEntity<Object> updateCategory(@NotBlank @Size(min=5,max=10) @ModelAttribute("categoryName") String cateName,@Min(value=0) @ModelAttribute("parentId") int parentId, BindingResult result){
//		return new ResponseEntity<Object>(sc.success(200, "Chỉnh sửa category thành công!", "success", "/updateCategory"), HttpStatus.OK);
//	}
}

