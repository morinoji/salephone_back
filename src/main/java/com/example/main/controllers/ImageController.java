package com.example.main.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.ResponseObject;
import com.example.main.models.Comment;
import com.example.main.models.ProductImage;
import com.example.main.repositories.ProductImageRepository;
import com.example.main.services.ImageService;

@RestController
public class ImageController {
	private ImageService imageSv;

	public ImageController(ImageService imageSv) {
		super();
		this.imageSv = imageSv;
		
	}
	
	@RequestMapping(value="/getImageByProd")
    public ResponseObject update(@NotNull @RequestParam("product_id") Integer product_id) {
        return new ResponseObject(200, "Thành công!", imageSv.findByProductId(product_id));
    }
}
