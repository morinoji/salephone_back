package com.example.main.services;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.Cart;
import com.example.main.repositories.CartRepository;

@Service
public class CartService {
	private CartRepository cartRepo;

	public CartService(CartRepository cartRepo) {
		super();
		this.cartRepo = cartRepo;
	}
	
	public Cart findById(int id) {
		 Cart cart=cartRepo.findByID(id);
		 if(cart.getCart_id()==0) {
		    		throw new NoDataFoundException();
		 }

	    return cart;
	}
	
	public void insertCart(int user_id, int product_id, int quantity) {
		  cartRepo.insertCart(user_id, product_id, quantity);
	}
}
