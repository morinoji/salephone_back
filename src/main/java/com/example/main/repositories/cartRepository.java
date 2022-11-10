package com.example.main.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.main.models.cart;
import com.example.main.models.cartDetail;


@Component
public class cartRepository {
	private JdbcTemplate jdbc;

	public cartRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public cart findByID(int user_id) {
		String query="Select cart.cart_id, cart.user_id,cart.created_at as cartCr, cart.updated_at as cartUp,cartdetail.quantity, product.title, product.thumbnail,product.price, cartdetail.created_at, cartdetail.updated_at from cart inner join cartdetail on cart.cart_id = cartdetail.cart_id inner join product on cartdetail.product_id=product.product_id where cart.user_id='"+user_id+"'";
		
		return jdbc.query(query, new ResultSetExtractor<cart>() {

			@Override
			public cart extractData(ResultSet rs) throws SQLException, DataAccessException {
				cart cart=new cart();
				List<Map<String,Object>> temp=new ArrayList<>();
				while(rs.next()) {
					cart.setCart_id(rs.getInt("cart_id"));
					cart.setUser_id(rs.getInt("user_id"));
					cart.setCreatedAt(rs.getString("cartCr"));
					cart.setUpdatedAt(rs.getString("cartUp"));
					Map<String, Object> mapTemp=new HashMap<>();
					mapTemp.put("title", rs.getString("title"));
					mapTemp.put("thumbnail", rs.getString("thumbnail"));
					mapTemp.put("quantity", rs.getInt("quantity"));
					mapTemp.put("price", rs.getString("price"));
					mapTemp.put("created_at", rs.getString("created_at"));
					mapTemp.put("updated_at", rs.getString("updated_at"));
					temp.add(mapTemp);
				}
				cart.setListProducts(temp);
				return cart;
			}
		});
	}
	public void insertCart(int user_id,int product_id, int quantity) {
		
		String getCartID="select cart_id from cart where user_id='"+user_id+"'";
		int cartid=jdbc.queryForObject(getCartID, Integer.class);
		String checkItemExist="select * from cartdetail where product_id='"+product_id+"' and cart_id='"+cartid+"'";
		cartDetail isExist=jdbc.queryForObject(checkItemExist, BeanPropertyRowMapper.newInstance(cartDetail.class));
		String query="";
		if(isExist==null) {
			query="insert into cartdetail(cart_id, product_id, quantity) values('"+cartid+"','"+product_id+"','"+quantity+"')";
		}else {
			query="update cartdetail set quantity='"+(isExist.getQuantity()+quantity)+"' where cart_id='"+cartid+"'";
		}
		
		jdbc.execute(query);
	}

}
