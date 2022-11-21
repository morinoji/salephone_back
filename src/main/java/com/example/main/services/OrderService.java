package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.Order;
import com.example.main.models.PostOrder;
import com.example.main.repositories.OrderRepository;

@Service
public class OrderService {
	private OrderRepository orderRepo;

	public OrderService(OrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}
	
	public List<Order> findByUser(int id, String status){
		 List<Order> temp=orderRepo.getByUser(id,status);
	    	if(temp.isEmpty()) {
	    		throw new NoDataFoundException();
	    	}
			return temp;
	}
	
	public void placeOrder(PostOrder postOrder) {
		orderRepo.placeOrder(postOrder);
	}
	
	public void updateStatus(Order order) {
		orderRepo.updateStatus(order.getOrder_id(), order.getStatus());
	}
	
	public void DeleteOrder(Order order) {
		orderRepo.DeleteOrder(order.getOrder_id());
	}
}
