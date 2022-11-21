package com.example.main.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.ResponseObject;
import com.example.main.services.DashBoardService;

@RestController
public class DashBoardController {
	private DashBoardService dbs;

	public DashBoardController(DashBoardService dbs) {
		super();
		this.dbs = dbs;
	}
	
	@RequestMapping("/getData")
	public ResponseEntity<Object> GetRevenue(@RequestParam("date") String date){
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Success!", dbs.GetRevenue(date)));
	}
}
