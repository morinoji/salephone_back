package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.models.banner;
import com.example.main.repositories.bannerRepository;

@Service
public class bannerService {
	private bannerRepository bannerRepo;

	public bannerService(bannerRepository bannerRepo) {
		super();
		this.bannerRepo = bannerRepo;
	}
	
	public List<banner> findAll(){
		return bannerRepo.findNewestBanners();
	}
}
