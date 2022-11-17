package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.models.Banner;
import com.example.main.repositories.BannerRepository;

@Service
public class BannerService {
	private BannerRepository bannerRepo;

	public BannerService(BannerRepository bannerRepo) {
		super();
		this.bannerRepo = bannerRepo;
	}
	
	public List<Banner> findAll(){
		return bannerRepo.findNewestBanners();
	}
}
