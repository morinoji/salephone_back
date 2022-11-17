package com.example.main.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.main.models.Banner;

@Repository
public class BannerRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Banner> findNewestBanners(){
		String query="select * from slider order by created_at desc";
		return jdbc.query(query, BeanPropertyRowMapper.newInstance(Banner.class));
	}
}
