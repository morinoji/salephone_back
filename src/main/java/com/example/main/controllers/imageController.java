package com.example.main.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.exceptions.successTemplate;
import com.example.main.models.ResponseObject;
import com.example.main.models.comment;
import com.example.main.models.productImage;
import com.example.main.repositories.productImageRepository;
import com.example.main.services.imageService;

@RestController
public class imageController {
	private imageService imageSv;
	private successTemplate scf;
	public imageController(imageService imageSv, successTemplate scf) {
		super();
		this.imageSv = imageSv;
		this.scf = scf;
	}
	
	@RequestMapping(value="/getImageByProd")
    public ResponseObject update(@NotNull @RequestParam("product_id") Integer product_id) {
        return new ResponseObject(200, "Thành công!", imageSv.findByProductId(product_id));
    }
}
