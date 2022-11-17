package com.example.main.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.main.models.ProductImage;

@Repository
public class ProductImageRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<ProductImage> getByProduct(int product_id) {
		String query="select * from productimage where product_id='"+product_id+"'";
		return jdbc.query(query, BeanPropertyRowMapper.newInstance(ProductImage.class));
	}
}
