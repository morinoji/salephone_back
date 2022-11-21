package com.example.main.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.Cart;
import com.example.main.models.Order;
import com.example.main.models.PostOrder;
import com.example.main.repositories.OrderRepository;
import com.example.main.services.OrderService;

@RestController
public class OrderController {
	private OrderService orderSv;

	private JwtTokenProvider tokenProvider;
	
	public OrderController(OrderService orderSv,JwtTokenProvider tokenProvider) {
		this.orderSv = orderSv;

		this.tokenProvider=tokenProvider;
	}

	@RequestMapping(value="/getOrdersById")
    public ResponseObject getOrderByUser(@ModelAttribute("status") String status,@ModelAttribute("id") int id) {
//	    String jwt=request.getHeader("Authorization").substring(7);
//		String email=tokenProvider.getUserIdFromJWT(jwt);
		List<Order> list=orderSv.findByUser(id, status);
        return new ResponseObject(200, "Thành công!", list);
    }  
	
	@RequestMapping(value="/placeOrder",method = RequestMethod.POST)
    public ResponseObject getThumbNail(@RequestBody PostOrder postOrder ) {
		orderSv.placeOrder(postOrder);
        return new ResponseObject(200, "Đặt hàng thành công!", null);
    }  
	@RequestMapping(value="/updateStatus",method = RequestMethod.PUT)
    public ResponseObject updateStatus(@RequestBody Order order ) {
		orderSv.updateStatus(order);
		return new ResponseObject(200, "Cập nhật trạng thái thành công!", null);
    }  
	
	@RequestMapping(value="/deleteOrder",method = RequestMethod.DELETE)
    public ResponseObject DeleteOrder(@RequestBody Order order) {
		orderSv.DeleteOrder(order);
		return new ResponseObject(200, "Xóa đơn hàng thành công!", null);
    }  
}
