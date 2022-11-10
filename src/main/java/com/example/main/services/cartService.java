package com.example.main.services;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.cart;
import com.example.main.repositories.cartRepository;

@Service
public class cartService {
	private cartRepository cartRepo;

	public cartService(cartRepository cartRepo) {
		super();
		this.cartRepo = cartRepo;
	}
	
	public cart findById(int id) {
		 cart cart=cartRepo.findByID(id);
		 if(cart.getCart_id()==0) {
		    		throw new NoDataFoundException();
		 }

	    return cart;
	}
	
	public void insertCart(int user_id, int product_id, int quantity) {
		  cartRepo.insertCart(user_id, product_id, quantity);
	}
}
