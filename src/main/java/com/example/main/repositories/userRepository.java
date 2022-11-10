package com.example.main.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.main.models.user;

@Component
@Repository
public class userRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	
	public List<user> getProfile(int id) {
		String query="Select * from usertable where user_id='"+id+"'";
		return jdbc.query(query, BeanPropertyRowMapper.newInstance(user.class));
	}
	
	public Integer findId(String email) {
		String query="Select * from usertable where user_email='"+email+"'";
		
		return jdbc.query(query, BeanPropertyRowMapper.newInstance(user.class)).get(0).getUser_id();
	}
	public user findByUserName(String email) {
		String query="Select * from usertable inner join role on usertable.role_id = role.role_id where usertable.user_email='"+email+"'";
		
		return jdbc.query(query, new ResultSetExtractor<user>() {
			@Override
			public user extractData(ResultSet rs) throws SQLException, DataAccessException {
				user account=new user();
				while(rs.next()) {
					account.setUser_id(rs.getInt("user_id"));
					account.setUser_name(rs.getString("user_name"));
					account.setUser_password(rs.getString("user_password"));
					account.setUser_email(rs.getString("user_email"));
					account.setUser_phone_number(rs.getString("user_phone_number"));
					account.setUser_date_of_birth(rs.getString("user_date_of_birth"));
					account.setUser_address(rs.getString("user_address"));
					account.setUser_fullname(rs.getString("user_fullname"));
					account.setUser_avatar(rs.getString("user_avatar"));
					account.setCreatedAt(rs.getString("created_at"));
					account.setUpdatedAt(rs.getString("updated_at"));
					account.setRoles(new SimpleGrantedAuthority(rs.getString("role_name")));
				}
				return account;
			}
		});
	}
	
	public user getUserById(int id) {
		String query="Select * from usertable inner join role on usertable.role_id = role.role_id where usertable.user_id='"+id+"'";
		
		return jdbc.query(query, new ResultSetExtractor<user>() {
			@Override
			public user extractData(ResultSet rs) throws SQLException, DataAccessException {
				user account=new user();
				while(rs.next()) {
					account.setUser_id(rs.getInt("user_id"));
					account.setUser_name(rs.getString("user_name"));
					account.setUser_password(rs.getString("user_password"));
					account.setUser_email(rs.getString("user_email"));
					account.setUser_phone_number(rs.getString("user_phone_number"));
					account.setUser_date_of_birth(rs.getString("user_date_of_birth"));
					account.setUser_address(rs.getString("user_address"));
					account.setUser_fullname(rs.getString("user_fullname"));
					account.setUser_avatar(rs.getString("user_avatar"));
					account.setCreatedAt(rs.getString("created_at"));
					account.setUpdatedAt(rs.getString("updated_at"));
					account.setRoles(new SimpleGrantedAuthority(rs.getString("role_name")));
				}
				return account;
			}
		});
	}
	
	public void insertCustomer(String email, String password) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String enPassword=encoder.encode(password);
		String query="insert into usertable(user_email, user_password,role_id) values('"+email+"','"+enPassword+"',4)";
		jdbc.execute(query);
	}
	
	public void updateAvatar( String avatar, String ogEmail ) {
		String query="update usertable set user_avatar='"+avatar+"' where user_email='"+ogEmail+"'";
		jdbc.execute(query);
	}
	
	public void updateCustomer(String fullname,String email,String phone_number, String dob, String address,  String ogEmail ) {
		String query="update usertable set user_fullname='"+fullname+"', user_date_of_birth=TO_DATE('"+dob+"','YYYY-MM-DD'),user_address='"+address+"',user_phone_number='"+phone_number+"',user_email='"+email+"' where user_email='"+ogEmail+"'";
		jdbc.execute(query);
	}
	
	public String changePassword(String email,String oldPassword, String password) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String oldPW=jdbc.queryForObject("select user_password from usertable where user_email='"+email +"'", BeanPropertyRowMapper.newInstance(user.class)).getPassword();
		
		boolean checkOldPw=encoder.matches(oldPassword, oldPW);
		if(checkOldPw==false) {
			return "Old password does not match!";
		}else {
			String enPassword=encoder.encode(password);
			String query="update usertable set user_password='"+enPassword+"' where user_email='"+email+"'";
			jdbc.execute(query);
			return "Success!";
		}
		
	}
}
