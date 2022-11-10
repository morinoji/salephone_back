package com.example.main.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.main.models.comment;
import com.example.main.models.order;

@Repository
public class commentRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<comment> getByProduct(int product_id, int limit, int offset) {
		String query="select * from comments inner join usertable on comments.user_id=usertable.user_id where product_id='"+product_id+"' order by comment_created_at desc OFFSET "+offset+" ROWS FETCH NEXT "+limit+" ROWS ONLY";
		return jdbc.query(query, new ResultSetExtractor<List<comment>>() {
			@Override
			public List<comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<comment> listCmt=new ArrayList<>();
				while(rs.next()) {
					comment cmt=new comment();
					cmt.setAvatar(rs.getString("user_avatar"));
					cmt.setComment_content(rs.getString("comment_content"));
					cmt.setComment_createdAt(rs.getString("comment_created_at"));
					cmt.setComment_id(rs.getInt("comment_id"));
					cmt.setComment_updatedAt(rs.getString("comment_updated_at"));
					cmt.setFullname(rs.getString("user_fullname"));
					cmt.setProduct_id(rs.getInt("product_id"));
					cmt.setStars(rs.getFloat("stars"));
					cmt.setUser_id(rs.getInt("user_id"));
					listCmt.add(cmt);
				}
				
				return listCmt;
			}
		});
	}
	
	@Transactional
	public void insertComment(String content, float star, int product_id, int user_id) {
		String query="insert into comments(user_id, product_id, comment_content, stars) values('"+user_id+"','"+product_id+"','"+content+"','"+star+"')";
		jdbc.execute(query);
		String updateProduct="update product set rated=rated+1, rating=rating+"+star+" where product_id="+product_id;
		jdbc.execute(updateProduct);
	}
}
