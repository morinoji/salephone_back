package com.example.main.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.exceptions.successTemplate;
import com.example.main.models.ResponseObject;
import com.example.main.models.comment;
import com.example.main.models.product;
import com.example.main.repositories.commentRepository;

@RestController
@Validated
public class commentController {
	private commentRepository cmtRepo;
	private successTemplate scf;
	public commentController(commentRepository cmtRepo, successTemplate scf) {
		this.cmtRepo = cmtRepo;
		this.scf = scf;
	}
	
	@RequestMapping(value="/getCmtByProd")
    public ResponseObject update(@NotNull @RequestParam("product_id") Integer product_id,@NotNull @RequestParam("limit") Integer limit,@NotNull @RequestParam("offset") Integer offset) {

    	List<comment> listProd=cmtRepo.getByProduct(product_id,limit,offset);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
//    	for(product prd:listProd) {		
//    		prd.setImages(productRepo.findThumbNail(prd.getProductId()));
//    	}
    	
    	
        
        return new ResponseObject(200, "Thành công!", listProd);
    }
	
	@RequestMapping(value="/postComment",method = RequestMethod.POST)
    public ResponseObject postComment(@Valid @RequestBody comment cmt) {
    	cmtRepo.insertComment(cmt.getComment_content(), cmt.getStars(), cmt.getProduct_id(), cmt.getUser_id());
        return new ResponseObject(200, "Đánh giá thành công!", null);
    }
}
