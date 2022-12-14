package com.example.main.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.main.models.Category;

//public interface categoryRepository extends PagingAndSortingRepository<category, Integer> {
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value="insert into categories(category_name, parent_id) values (?1, ?2)",nativeQuery = true)
//	void insert(String name, int parentId );
//}
@Component
public class CategoryRepository{
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Category> findAll(){
		String sql="select * from categories";

		return jdbc.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
	}
	
	public String addNewCategory(String category_name) {
		jdbc.update("insert into categories(category_name) values('"+category_name+"')");
		return "Thêm danh mục thành công!";
	}
	
	public String editCategory(int category_id, String category_name) {
		jdbc.update("update categories set category_name='"+category_name+"' where category_id='"+category_id+"'");
		return "Chỉnh sử danh mục thành công!";
	}
	
	public String deleteCategory(int category_id) {
		jdbc.update("delete from categories where category_id='"+category_id+"'");
		jdbc.update("delete from product where category_id='"+category_id+"'");
		return "Xóa danh mục thành công!";
	}
}