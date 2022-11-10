package com.example.main.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.main.models.category;

//public interface categoryRepository extends PagingAndSortingRepository<category, Integer> {
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value="insert into categories(category_name, parent_id) values (?1, ?2)",nativeQuery = true)
//	void insert(String name, int parentId );
//}
@Component
public class categoryRepository{
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<category> findAll(){
		String sql="select * from categories";

		return jdbc.query(sql, BeanPropertyRowMapper.newInstance(category.class));
	}
	
}