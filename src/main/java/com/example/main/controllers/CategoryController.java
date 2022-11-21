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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.Category;
import com.example.main.models.User;
import com.example.main.repositories.CategoryRepository;
import com.example.main.repositories.UserRepository;
import com.example.main.services.CategoryService;

@RestController
@Validated
public class CategoryController {

	private CategoryService cateSv;
	private UserRepository userRepo;
	
	
public CategoryController(CategoryService cateSv, UserRepository userRepo) {
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
	
	
	@RequestMapping(value="/deleteCategory", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCategory(@RequestBody Category category){
		return new ResponseEntity<Object>(new ResponseObject(200, cateSv.deleteCategory(category.getCategoryId()),null), HttpStatus.OK);
	}
	
	@RequestMapping(value="/addCategory", method = RequestMethod.POST)
	public ResponseEntity<Object> addCategory(@RequestBody Category category){
		return new ResponseEntity<Object>(new ResponseObject(200, cateSv.addNewCategory(category.getCategoryName()),null), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updateCategory", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCategory(@RequestBody Category category){
		return new ResponseEntity<Object>(new ResponseObject(200, cateSv.editCategory(category.getCategoryId(), category.getCategoryName()),null), HttpStatus.OK);
	}
}

