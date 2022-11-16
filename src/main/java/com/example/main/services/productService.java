package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.product;
import com.example.main.repositories.productRepository;
import com.example.main.utils.Utils;

@Service
public class productService {
	private productRepository productRepo;

	public productService(productRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	public List<product> findByCate(int category_id){
		List<product> listProd=productRepo.findByCategory(category_id);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public List<product> search( String searchText){
    	List<product> listProd=productRepo.search(Utils.toSlug(searchText));
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public product getDetail(String slug) {
		product detailProd=productRepo.getDetail(slug);
		if(detailProd==null) {
    		throw new NoDataFoundException();
    	}
		return detailProd;
	}
	
	public List<product> listingProd(String field){
		List<product> listProd=productRepo.listingProds(field);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
    	return listProd;
	}
	
	public List<product> listingAllProds(int limit, int offset){
		List<product> listProd=productRepo.ListingAllProds(offset, limit);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
    	return listProd;
	}
}
