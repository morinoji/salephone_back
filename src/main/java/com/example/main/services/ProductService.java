package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.Product;
import com.example.main.repositories.ProductRepository;
import com.example.main.utils.Utils;

@Service
public class ProductService {
	private ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	public List<Product> findByCate(int category_id){
		List<Product> listProd=productRepo.findByCategory(category_id);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public List<Product> search( String searchText){
    	List<Product> listProd=productRepo.search(Utils.toSlug(searchText));
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public Product getDetail(String slug) {
		Product detailProd=productRepo.getDetail(slug);
		if(detailProd==null) {
    		throw new NoDataFoundException();
    	}
		return detailProd;
	}
	
	public List<Product> listingProd(String field){
		List<Product> listProd=productRepo.listingProds(field);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
    	return listProd;
	}
	
	public List<Product> listingAllProds(){
		List<Product> listProd=productRepo.ListingAllProds();
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
    	return listProd;
	}
	
	public String addProduct(Product product) {
		int id=productRepo.addProduct(product);
		return id+"";
	}
	
	public String EditProduct(Product product) {
		String result=productRepo.EditProduct(product);
		return result;
	}
	
	public String uploadImages(String thumbnail, int product_id, List<String> imageList) {
		String result=productRepo.uploadImages(thumbnail, product_id, imageList);
		return result;
	}
	
	public String RemoveImages(String thumbNail, List<String> imageList) {
		String result=productRepo.RemoveImages(thumbNail, imageList);
		return result;
	}
}
