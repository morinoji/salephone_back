package com.example.main.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.example.main.models.order;
import com.example.main.models.orderDetail;
import com.example.main.models.postOrder;
import com.example.main.models.user;

@Repository
public class orderRepository {
	private JdbcTemplate jdbc;
	private userRepository userRepo;
	
	public orderRepository(JdbcTemplate jdbc,userRepository userRepo) {
		this.jdbc = jdbc;
		this.userRepo=userRepo;
	}
	
	public void placeOrder(postOrder ord) {
		String query="insert into orders(user_id, address, status) values(?,?,'Đang giao')";
		jdbc.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ord.getUser_id());
				ps.setString(2, ord.getAddress());
			}
		} );
		int order_id=jdbc.query("select order_id from orders where user_id=? order by order_created_at desc", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, ord.getUser_id());
			}
		}, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet resultSet) throws SQLException,
            DataAccessException {
              if (resultSet.next()) {
                  return resultSet.getInt(1);
              }
              return null;
          }
        });
		
		for(orderDetail ordDT:ord.getDetailList()) {
			jdbc.execute("insert into orderdetail(order_id, product_id, quantity) values('"+order_id+"','"+ordDT.getProduct_id()+"','"+ordDT.getQuantity()+"')");
		}
		
	}
	
	public List<order> getByUser(String email,String status) {
			Integer id=userRepo.findId(email);
			String query="Select * from orders left join orderdetail on orders.order_id = orderdetail.order_id inner join product on orderdetail.product_id=product.product_id where orders.user_id="+id+" and orders.status like '%"+status+"%'";
			
			return jdbc.query(query, new ResultSetExtractor<List<order>>() {
				@Override
				public List<order> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<order> orderList=new ArrayList<>();
					List<Map<String, Object>> detailList=new ArrayList<>();
					order ord=new order();
					int recordCount=0;
					while(rs.next()) {
						recordCount=rs.getRow();
						if( ord.getOrder_id()!=null && rs.getInt("order_id") != ord.getOrder_id()) {
							ord.setListProd(detailList);
							detailList=new ArrayList<>();
							orderList.add(ord);
							ord=new order();
					}
						ord.setOrder_id(rs.getInt("order_id"));
						ord.setUser_id(rs.getInt("user_id"));
						ord.setAddress(rs.getString("address"));
						ord.setStatus(rs.getString("status"));
						ord.setOrder_created_at(rs.getString("order_created_at"));
						ord.setOrder_updated_at(rs.getString("order_updated_at"));
						if(rs.getInt("orderdetail_id")!=0) {
							Map<String, Object> map=new HashMap<>();
							map.put("orderdetail_id", rs.getInt("orderdetail_id"));
							map.put("product_id", rs.getInt("product_id"));
							map.put("quantity", rs.getInt("quantity"));
							map.put("title", rs.getString("title"));
							map.put("thumbnail", rs.getString("thumbnail"));
							map.put("orderdetail_created_at", rs.getString("orderdetail_created_at"));
							map.put("orderdetail_updated_at", rs.getString("orderdetail_updated_at"));
							detailList.add(map);
						}
					}
					if(recordCount>1) {
						ord.setListProd(detailList);
						orderList.add(ord);
					}
					return orderList;
				}
			});

	}
	
	public void updateStatus(int order_id, String status) {
		String query="update orders set status=? where order_id=?";
		jdbc.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(2, order_id);
				ps.setString(1, status);
				
			}
		});
	}
	
}
