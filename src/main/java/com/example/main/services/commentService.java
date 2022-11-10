package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.comment;
import com.example.main.repositories.commentRepository;

@Service
public class commentService {
	private commentRepository cmtRepo;

	public commentService(commentRepository cmtRepo) {
		super();
		this.cmtRepo = cmtRepo;
	}
	
	public List<comment> findById(int product_id, int limit, int offset){
		List<comment> listProd=cmtRepo.getByProduct(product_id,limit,offset);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public void postComment(comment cmt) {
		cmtRepo.insertComment(cmt.getComment_content(), cmt.getStars(), cmt.getProduct_id(), cmt.getUser_id());
	}
}
