package com.example.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.main.config.Constant;
import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.ResponseObject;
import com.example.main.models.Product;
import com.example.main.models.ProductImage;
import com.example.main.repositories.ProductRepository;
import com.example.main.services.ProductService;
import com.example.main.utils.Utils;

@RestController
public class ProductController {
	private ProductService productSv;

	public ProductController(ProductService productSv) {
		this.productSv = productSv;

	}

	@RequestMapping(value="/listingProduct")
    public ResponseObject listingProduct(@NotNull @RequestParam("field") String field) {
        return new ResponseObject(200, "Thành công!", productSv.listingProd(field));
    }
	
	@RequestMapping(value="/listing-all-product")
    public ResponseObject listingProduct(@NotNull @RequestParam("limit") Integer limit, @NotNull @RequestParam("offset") Integer offset) {
        return new ResponseObject(200, "Thành công!", productSv.listingAllProds(offset, limit));
    }

	@RequestMapping(value="/getProdsByCate")
	    public ResponseObject update(@NotNull @RequestParam("category_id") Integer category_id) {
	        return new ResponseObject(200, "Thành công!", productSv.findByCate(category_id));
	    }
	    
    
    
    @RequestMapping(value="/search")
    public ResponseObject searchProduct(@Null @RequestParam(value="category_id", required=false) Integer category_id,@Null @RequestParam(value="searchText", required=false) String searchText) {
    	return new ResponseObject(200, "Thành công!", productSv.search(Utils.toSlug(searchText)));
    }  
    
    
    @RequestMapping(value="/detail")
    public ResponseObject searchProduct(@NotNull @RequestParam(value="prd") String slug) {
        return new ResponseObject(200, "Thành công!", productSv.getDetail(slug));
    }  
    
    @RequestMapping(value="/addProduct", method = RequestMethod.POST)
    public ResponseObject addProduct(@RequestBody Product product) {
        return new ResponseObject(200, "Thành công!", productSv.addProduct(product));
    }  
    
    @RequestMapping(value="/uploadProductImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseObject uploadProductImage(@RequestPart MultipartFile thumbnail ,@RequestPart MultipartFile[] imageList, @ModelAttribute("product_id") int product_id) throws IOException {
    	List<String> imageNameList=new ArrayList<>();
        byte[] bytes = thumbnail.getBytes();
    	String genName=UUID.randomUUID().toString();
    	String finalName=genName+"."+ thumbnail.getOriginalFilename().split("\\.")[1];
		Path path = Paths.get(Constant.imageDir+"thumbnails/" + finalName);
		Files.write(path, bytes);
		for(MultipartFile element:imageList) {
				byte[] bytesTemp = element.getBytes();
		    	String genNameTemp=UUID.randomUUID().toString();
		    	String finalNameTemp=genNameTemp+"."+ thumbnail.getOriginalFilename().split("\\.")[1];
				Path pathTemp = Paths.get(Constant.imageDir+"products/" + finalNameTemp);
				Files.write(pathTemp, bytesTemp);
				imageNameList.add(finalNameTemp);
		}
		productSv.uploadImages(finalName, product_id, imageNameList);
        return new ResponseObject(200, "Upload ảnh thành công!", null);
    }  
    
}
