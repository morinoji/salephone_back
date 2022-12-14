package com.example.main.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.main.models.Color;
import com.example.main.models.Comment;
import com.example.main.models.Product;
import com.example.main.models.ProductDetail;
import com.example.main.models.ProductImage;
import com.example.main.models.User;
import com.example.main.utils.Utils;

@Component
@Repository
public class ProductRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Product> listingProds(String field){
		String query="select * from product order by "+field+" desc OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY";
		List<Product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(Product.class));
		return listProd;
	}
	
	public List<Product> ListingAllProds(){
		String query="select * from product ";
		List<Product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(Product.class));
		return listProd;
	}
	
	public List<Product> findByCategory(int categoryId){
		String query="Select * from product where category_id='"+categoryId+"'";
		List<Product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(Product.class));
		return listProd;
	}
	
	public Map<String, String> findById(int productId){
		String query="Select title,thumbnail  from product where product_id='"+productId+"'";
		Product listProd=jdbc.queryForObject(query, BeanPropertyRowMapper.newInstance(Product.class));
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
	
	public List<Product> search(String searchText){
		String search=searchText==null? "":searchText;
		String query="";
		
			query="Select * from product where slug like '%"+search+"%'";
		
		List<Product> listProd=jdbc.query(query, BeanPropertyRowMapper.newInstance(Product.class));
		return listProd;
	}
	
	public Product getDetail(String slug) {
		String query="Select * from product inner join productdetail on product.detail_id = productdetail.detail_id left join productimage on product.product_id=productimage.product_id where product.slug='"+slug+"'";
		
		return jdbc.query(query, new ResultSetExtractor<Product>() {

			@Override
			public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
				Product prod=new Product();
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
						prod.setDetail(new ProductDetail(rs.getInt("detail_id"),rs.getString("model_name"),rs.getString("detail_screen"),rs.getString("detail_os"), rs.getString("detail_behindcam"),rs.getString("detail_frontcam"),rs.getString("detail_chip"),rs.getString("detail_ram"),rs.getString("detail_internalmem"),rs.getString("detail_sim"),rs.getString("detail_pin"), rs.getString("detail_created_at"),rs.getString("detail_updated_at")));
					}
					if(rs.getString("image_name")!=null) {
						listImage.add(rs.getString("image_name"));
					}
//					System.out.print(rs.getString("present").split("\\+"));
					prod.setPresentList(Arrays.asList(rs.getString("presents").split("\\+")));
					prod.setColorList(Arrays.asList(rs.getString("colors").split("\\+")));
//					cmt.add(new comment(rs.getInt("comment_id"),rs.getInt("user_id"), rs.getInt("product_id"),rs.getString("comment_content"),rs.getFloat("stars"),rs.getString("comment_created_at"),rs.getString("comment_updated_at")));
//					images.add(new productImage(rs.getInt("image_id"),rs.getString("image_name"),rs.getInt("product_id"),rs.getString("image_created_at"),rs.getString("image_updated_at")));
				}
				prod.setImageList(listImage);
				
				return prod;
			}
		});
	}
	
	public int returnProductDetailId(ProductDetail detail ) {
		String query="insert into productdetail(model_name, detail_screen, detail_os, detail_behindcam, detail_frontcam, detail_chip, detail_ram, detail_internalmem, detail_sim, detail_pin) values('"+detail.getModel_name()+"','"+detail.getDetail_screen()+"','"+detail.getDetail_os()+"','"+detail.getDetail_behindcam()+"','"+detail.getDetail_frontcam()+"','"+detail.getDetail_chip()+"','"+detail.getDetail_ram()+"','"+detail.getDetail_internalmem()+"','"+detail.getDetail_sim()+"','"+detail.getDetail_pin()+"')";		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(
				new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				        return connection.prepareStatement(query, new String[] {"detail_id"});
				      }
				    }, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	public int addProduct(Product product) {
		int detailId=returnProductDetailId(product.getDetail());
		String query="insert into product(title,product_content, price, brand, category_id, detail_id, slug, colors, presents) values ('"+product.getTitle()+"','"+product.getProduct_content()+"','"+product.getPrice()+"','"+product.getBrand()+"','"+product.getCategory_id()+"','"+detailId+"','"+Utils.toSlug(product.getTitle())+"','"+product.getColors()+"','"+product.getPresents()+"')";
				
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(
				new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				        return connection.prepareStatement(query, new String[] {"product_id"});
				      }
				    }, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	
	public String uploadImages(String thumbNail, int productId, List<String> imageList) {
		if(thumbNail!="") {
			String query="update product set thumbnail='"+thumbNail+"' where product_id='"+productId+"'";
			jdbc.update(query);
		}
		
		if(!imageList.isEmpty()) {
			for(String element:imageList) {
				jdbc.update("insert into productimage(image_name, product_id) values(?,?)", new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, element);
					ps.setInt(2, productId);
				}
			});
		}
		}
		
		return "Upload images success!";
	}
	
	public String RemoveImages(String thumbNail, List<String> imageList) {
		if(thumbNail!=null || thumbNail!="") {
			String query="delete from product where thumbnail=? ";
			jdbc.update(query, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, thumbNail);
					
				}
			});
		}

		if(!imageList.isEmpty()) {
			for(String element:imageList) {
				jdbc.update("delete from  productimage where image_name=?", new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, element);
				}
			});
		}
		}
		
		return "Remove images Success!";
	}
	
	public String EditProductDetail(ProductDetail detail ) {
		String query="update productdetail set model_name= ?, detail_screen=?, detail_os=?, detail_behindcam=?, detail_frontcam=?, detail_chip=?, detail_ram=?, detail_internalmem=?, detail_sim=?, detail_pin=?  where detail_id='"+detail.getDetail_id()+"'";		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, detail.getModel_name());
				ps.setString(2, detail.getDetail_screen());
				ps.setString(3, detail.getDetail_os());
				ps.setString(4, detail.getDetail_behindcam());
				ps.setString(5, detail.getDetail_frontcam());
				ps.setString(6, detail.getDetail_chip());
				ps.setString(7, detail.getDetail_ram());
				ps.setString(8, detail.getDetail_internalmem());
				ps.setString(9, detail.getDetail_sim());
				ps.setString(10, detail.getDetail_pin());
			}
		});
		
		return "Success";
	}
	
	public String EditProduct(Product product ) {
		EditProductDetail(product.getDetail());
		String query="update product set title=?,product_content=?, price=?, brand=?, category_id=?, slug=?, colors=?, presents=? where product_id='"+product.getProduct_id()+"'";	

		jdbc.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, product.getTitle());
				ps.setString(2, product.getProduct_content());
				ps.setLong(3, product.getPrice());
				ps.setString(4, product.getBrand());
				ps.setInt(5, product.getCategory_id());
				ps.setString(6, Utils.toSlug(product.getTitle()));
				ps.setString(7, product.getColors());
				ps.setString(8, product.getPresents());
			}
		});
		
		return "Ch???nh s???a s???n ph???m th??nh c??ng!";
	}
}
