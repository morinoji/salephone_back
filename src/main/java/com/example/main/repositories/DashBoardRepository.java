package com.example.main.repositories;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.main.models.OrderDetail;
import com.example.main.utils.Utils;

@Component
@Repository
public class DashBoardRepository {
	private JdbcTemplate jdbc;

	public DashBoardRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}
	
	public Map<String, Object> GetRevenue(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 LocalDate dateParse = LocalDate.parse(date, formatter);
		 dateParse=dateParse.withDayOfMonth(
				 dateParse.getMonth().length(dateParse.isLeapYear()));
		 System.out.print(dateParse);
		Map<String,Object> finalMap=new HashMap<>();
		List<Map<String,Object>> yearRevenue=new ArrayList<>();
		String queryRevenue="select sum(product.price * orderdetail.quantity) as revenue, sum(orderdetail.quantity) as phone_sold from orderdetail inner join product on orderdetail.product_id = product.product_id where orderdetail_created_at > TO_DATE('"+date+"','dd/mm/yyyy')";
		String queryAccount="select count(*) as new_user from usertable where role_id=4 and created_at > TO_DATE('"+date+"','dd/mm/yyyy')";
		for(int i=1;i<=dateParse.getMonthValue();i++) {
			Map<String,Object> tempMap=new HashMap<>();
			String tempQuery="select sum(product.price * orderdetail.quantity) as revenue from orderdetail inner join product on orderdetail.product_id = product.product_id where orderdetail_created_at > TO_DATE('"+01+"/"+i+"/"+dateParse.getYear()+"','dd/mm/yyyy') and orderdetail_created_at < TO_DATE('\"+30+\"/\"+i+\"/\"+dateParse.getYear()+\"','dd/mm/yyyy')";
			tempMap.putAll(jdbc.queryForMap(tempQuery));
			yearRevenue.add();
		}
		finalMap.putAll(jdbc.queryForMap(queryRevenue));
		finalMap.putAll(jdbc.queryForMap(queryAccount));
		return  finalMap;
	}
}
