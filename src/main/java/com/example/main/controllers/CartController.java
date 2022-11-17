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
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.Cart;
import com.example.main.models.CartDetail;
import com.example.main.models.Product;
import com.example.main.models.ProductImage;
import com.example.main.repositories.CartRepository;
import com.example.main.repositories.ProductRepository;
import com.example.main.services.CartService;

@RestController
@Validated
public class CartController {
	private CartService cartSv;
	private ProductRepository prodRepo;

	private JwtTokenProvider tokenProvider;

	  public CartController(CartService cartSv, ProductRepository prodRepo,
			JwtTokenProvider tokenProvider) {
		this.cartSv = cartSv;
		this.prodRepo = prodRepo;

		this.tokenProvider = tokenProvider;
	}
	@RequestMapping(value="/getCartById")
	    public ResponseObject getCart(HttpServletRequest request) {
		  String jwt=request.getHeader("Authorization").substring(7);
	    	int id=Integer.parseInt(tokenProvider.getUserIdFromJWT(jwt));
		  Cart cart=cartSv.findById(id);
	      return new ResponseObject(200,"Thành công!", cart);
	    }  
	  @RequestMapping(value="/addToCart",method=RequestMethod.POST)
	    public ResponseObject add2Cart(@NotNull @ModelAttribute("user_id") Integer user_id,@NotNull @ModelAttribute("product_id") Integer product_id,@NotNull @ModelAttribute("quantity") Integer quantity) {
		  	cartSv.insertCart(user_id, product_id, quantity);
	    	
	    	return new ResponseObject(200, "Thêm vào giỏ hàng thành công!", null);
	    }  
}
