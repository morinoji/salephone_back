package com.example.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.exceptions.successTemplate;
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.cart;
import com.example.main.models.cartDetail;
import com.example.main.models.product;
import com.example.main.models.productImage;
import com.example.main.repositories.cartRepository;
import com.example.main.repositories.productRepository;
import com.example.main.services.cartService;

@RestController
@Validated
public class cartController {
	private cartService cartSv;
	private productRepository prodRepo;
	private successTemplate scf;
	private JwtTokenProvider tokenProvider;

	  public cartController(cartService cartSv, productRepository prodRepo, successTemplate scf,
			JwtTokenProvider tokenProvider) {
		this.cartSv = cartSv;
		this.prodRepo = prodRepo;
		this.scf = scf;
		this.tokenProvider = tokenProvider;
	}
	@RequestMapping(value="/getCartById")
	    public ResponseObject getCart(HttpServletRequest request) {
		  String jwt=request.getHeader("Authorization").substring(7);
	    	int id=Integer.parseInt(tokenProvider.getUserIdFromJWT(jwt));
		  cart cart=cartSv.findById(id);
	      return new ResponseObject(200,"Thành công!", cart);
	    }  
	  @RequestMapping(value="/addToCart",method=RequestMethod.POST)
	    public ResponseObject add2Cart(@NotNull @ModelAttribute("user_id") Integer user_id,@NotNull @ModelAttribute("product_id") Integer product_id,@NotNull @ModelAttribute("quantity") Integer quantity) {
		  	cartSv.insertCart(user_id, product_id, quantity);
	    	
	    	return new ResponseObject(200, "Thêm vào giỏ hàng thành công!", null);
	    }  
}
