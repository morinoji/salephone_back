package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.order;
import com.example.main.models.postOrder;
import com.example.main.repositories.orderRepository;

@Service
public class orderService {
	private orderRepository orderRepo;

	public orderService(orderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}
	
	public List<order> findByUser(String email, String status){
		 List<order> temp=orderRepo.getByUser(email,status);
	    	if(temp.isEmpty()) {
	    		throw new NoDataFoundException();
	    	}
			return temp;
		
	}
	
	public void placeOrder(postOrder postOrder) {
		orderRepo.placeOrder(postOrder);
	}
	
	public void updateStatus(order order) {
		orderRepo.updateStatus(order.getOrder_id(), order.getStatus());
	}
}
