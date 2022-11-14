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
import com.example.main.exceptions.successTemplate;
import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.ResponseObject;
import com.example.main.models.cart;
import com.example.main.models.order;
import com.example.main.models.postOrder;
import com.example.main.repositories.orderRepository;
import com.example.main.services.orderService;

@RestController
public class orderController {
	private orderService orderSv;
	private successTemplate scf;
	private JwtTokenProvider tokenProvider;
	
	public orderController(orderService orderSv, successTemplate scf,JwtTokenProvider tokenProvider) {
		this.orderSv = orderSv;
		this.scf = scf;
		this.tokenProvider=tokenProvider;
	}

	@RequestMapping(value="/getOrdersById")
    public ResponseObject getOrderByUser(@ModelAttribute("status") String status,@ModelAttribute("email") String email, HttpServletRequest request) {
//	    String jwt=request.getHeader("Authorization").substring(7);
//		String email=tokenProvider.getUserIdFromJWT(jwt);
		List<order> list=orderSv.findByUser(email, status);
        return new ResponseObject(200, "Thành công!", list);
    }  
	
	@RequestMapping(value="/placeOrder",method = RequestMethod.POST)
    public ResponseObject getThumbNail(@RequestBody postOrder postOrder ) {
		orderSv.placeOrder(postOrder);
        return new ResponseObject(200, "Đặt hàng thành công!", null);
    }  
	@RequestMapping(value="/updateStatus",method = RequestMethod.POST)
    public ResponseObject updateStatus(@RequestBody order order ) {
		orderSv.updateStatus(order);
		return new ResponseObject(200, "Cập nhật trạng thái thành công!", null);
    }  
}
