package com.example.main.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.main.models.OrderDetail;
import com.example.main.repositories.DashBoardRepository;

@Service
public class DashBoardService {
	private DashBoardRepository dbr;

	public DashBoardService(DashBoardRepository dbr) {
		super();
		this.dbr = dbr;
	}
	
	public Map<String, Object>  GetRevenue(String date){
		return dbr.GetRevenue(date);
	}
}
