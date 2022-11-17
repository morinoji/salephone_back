package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.exceptions.NoDataFoundException;
import com.example.main.models.Comment;
import com.example.main.repositories.CommentRepository;

@Service
public class CommentService {
	private CommentRepository cmtRepo;

	public CommentService(CommentRepository cmtRepo) {
		super();
		this.cmtRepo = cmtRepo;
	}
	
	public List<Comment> findById(int product_id, int limit, int offset){
		List<Comment> listProd=cmtRepo.getByProduct(product_id,limit,offset);
    	if(listProd.isEmpty()) {
    		throw new NoDataFoundException();
    	}
		return listProd;
	}
	
	public void postComment(Comment cmt) {
		cmtRepo.insertComment(cmt.getComment_content(), cmt.getStars(), cmt.getProduct_id(), cmt.getUser_id());
	}
}
