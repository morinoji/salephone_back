package com.example.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.ResponseObject;
import com.example.main.models.banner;
import com.example.main.repositories.bannerRepository;
import com.example.main.services.bannerService;

@RestController
public class bannerController {
	private bannerService bannerSv;

	
public bannerController(bannerService bannerSv) {
		super();
		this.bannerSv = bannerSv;
	}


	
	@RequestMapping("/getNewestBanners")
	public ResponseObject getNewest(){
		return new ResponseObject(200, "Thành công!",bannerSv.findAll());
	}
}
