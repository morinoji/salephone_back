package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.ProductImage;
import com.example.main.repositories.ProductImageRepository;

@Service
public class ImageService {
	private ProductImageRepository imageRepo;

	public ImageService(ProductImageRepository imageRepo) {
		super();
		this.imageRepo = imageRepo;
	}
	
	public List<ProductImage> findByProductId(int product_id){
		List<ProductImage> listProd=imageRepo.getByProduct(product_id);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
}
