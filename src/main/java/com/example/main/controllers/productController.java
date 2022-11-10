package com.example.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.exceptions.successTemplate;
import com.example.main.models.ResponseObject;
import com.example.main.models.product;
import com.example.main.models.productImage;
import com.example.main.repositories.productRepository;
import com.example.main.services.productService;
import com.example.main.utils.Utils;

@RestController
public class productController {
	private productService productSv;
	private successTemplate scf;

	public productController(productService productSv, successTemplate scf) {
		this.productSv = productSv;
		this.scf = scf;
	}

	@RequestMapping(value="/listingProduct")
    public ResponseObject listingProduct(@NotNull @RequestParam("field") String field) {
        return new ResponseObject(200, "Thành công!", productSv.listingProd(field));
    }
	
	@RequestMapping(value="/listing-all-product")
    public ResponseObject listingProduct(@NotNull @RequestParam("limit") Integer limit, @NotNull @RequestParam("offset") Integer offset) {
        return new ResponseObject(200, "Thành công!", productSv.listingAllProds(offset, limit));
    }

	@RequestMapping(value="/getProdsByCate")
	    public ResponseObject update(@NotNull @RequestParam("category_id") Integer category_id) {
	        return new ResponseObject(200, "Thành công!", productSv.findByCate(category_id));
	    }
	    
    
    
    @RequestMapping(value="/search")
    public ResponseObject searchProduct(@Null @RequestParam(value="category_id", required=false) Integer category_id,@Null @RequestParam(value="price",required=false) Integer price,@Null @RequestParam(value="searchText", required=false) String searchText) {
    	return new ResponseObject(200, "Thành công!", productSv.search(category_id,price,Utils.toSlug(searchText)));
    }  
    @RequestMapping(value="/detail")
    public ResponseObject searchProduct(@NotNull @RequestParam(value="prd") String slug) {
        return new ResponseObject(200, "Thành công!", productSv.getDetail(slug));
    }  
    
    
}
