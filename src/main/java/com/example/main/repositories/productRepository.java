package com.example.main.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.main.models.color;
import com.example.main.models.comment;
import com.example.main.models.product;
import com.example.main.models.productDetail;
import com.example.main.models.productImage;
import com.example.main.models.user;

@Component
@Repository
public class productRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<product> listingProds(String field){
		String query="select * from product order by "+field+" desc OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY";
		List<product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(product.class));
		return listProd;
	}
	
	public List<product> ListingAllProds(int limit, int offset){
		String query="select * from product OFFSET "+offset+" ROWS FETCH NEXT "+limit+" ROWS ONLY";
		List<product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(product.class));
		return listProd;
	}
	
	public List<product> findByCategory(int category_id){
		String query="Select * from product where category_id='"+category_id+"'";
		List<product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(product.class));
		return listProd;
	}
	
	public Map<String, String> findById(int product_id){
		String query="Select title,thumbnail  from product where product_id='"+product_id+"'";
		product listProd=jdbc.queryForObject(query, BeanPropertyRowMapper.newInstance(product.class));
		Map<String, String> tempProd=new HashMap<>();
		tempProd.put("title", listProd.getTitle());
		tempProd.put("thumbnail", listProd.getThumbnail());
		return tempProd;
	}
	
//	public List<productImage> findThumbNail(int product_id) {
//		String query="Select * from productimage where product_id='"+product_id+"' OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY";
//		List<productImage> thumbNail= jdbc.query(query, BeanPropertyRowMapper.newInstance(productImage.class));
//
//		return thumbNail;
//	}
	
	public List<product> search(String searchText){
		String search=searchText==null? "":searchText;
		String query="";
		
			query="Select * from product where slug like '%"+search+"%'";
		
		List<product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(product.class));
		return listProd;
	}
	
	public product getDetail(String slug) {
		String query="Select * from product inner join productdetail on product.detail_id = productdetail.detail_id left join productimage on product.product_id=productimage.product_id where product.slug='"+slug+"'";
		
		return jdbc.query(query, new ResultSetExtractor<product>() {

			@Override
			public product extractData(ResultSet rs) throws SQLException, DataAccessException {
				product prod=new product();
				Set<String> listImage=new HashSet<>();
				while(rs.next()) {

					prod.setProduct_id(rs.getInt("product_id"));
					prod.setTitle(rs.getString("title"));
					prod.setProduct_content(rs.getString("product_content"));
					prod.setPrice(rs.getLong("price"));
					prod.setRating(rs.getFloat("rating"));
					prod.setRated(rs.getInt("rated"));
					prod.setBrand(rs.getString("brand"));
					prod.setSlug(rs.getString("slug"));
					prod.setThumbnail(rs.getString("thumbnail"));
					prod.setCategory_id(rs.getInt("category_id"));
					prod.setCreatedAt(rs.getString("created_at"));
					prod.setUpdatedAt(rs.getString("updated_at"));
					prod.setStar1(rs.getInt("star1"));
					prod.setStar2(rs.getInt("star2"));
					prod.setStar3(rs.getInt("star3"));
					prod.setStar4(rs.getInt("star4"));
					prod.setStar5(rs.getInt("star5"));
					if(prod.getDetail()==null) {
						prod.setDetail(new productDetail(rs.getInt("detail_id"),rs.getString("model_name"),rs.getString("detail_screen"),rs.getString("detail_os"), rs.getString("detail_behindcam"),rs.getString("detail_frontcam"),rs.getString("detail_chip"),rs.getString("detail_ram"),rs.getString("detail_internalmem"),rs.getString("detail_sim"),rs.getString("detail_pin"), rs.getString("detail_created_at"),rs.getString("detail_updated_at")));
					}
					listImage.add(rs.getString("image_name"));
//					System.out.print(rs.getString("present").split("\\+"));
					prod.setPresents(Arrays.asList(rs.getString("presents").split("\\+")));
					prod.setColors(Arrays.asList(rs.getString("colors").split("\\+")));
//					cmt.add(new comment(rs.getInt("comment_id"),rs.getInt("user_id"), rs.getInt("product_id"),rs.getString("comment_content"),rs.getFloat("stars"),rs.getString("comment_created_at"),rs.getString("comment_updated_at")));
//					images.add(new productImage(rs.getInt("image_id"),rs.getString("image_name"),rs.getInt("product_id"),rs.getString("image_created_at"),rs.getString("image_updated_at")));
				}
				prod.setImageList(listImage);
				
//				prod.setComments(cmt);
				return prod;
			}
		});
	}
}
