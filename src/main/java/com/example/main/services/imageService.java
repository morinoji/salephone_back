package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.productImage;
import com.example.main.repositories.productImageRepository;

@Service
public class imageService {
	private productImageRepository imageRepo;

	public imageService(productImageRepository imageRepo) {
		super();
		this.imageRepo = imageRepo;
	}
	
	public List<productImage> findByProductId(int product_id){
		List<productImage> listProd=imageRepo.getByProduct(product_id);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
}
