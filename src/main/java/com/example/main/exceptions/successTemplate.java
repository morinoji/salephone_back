package com.example.main.exceptions;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class successTemplate {
	public Map<String, Object> success(int code, String message, Object data) {
		Map<String,Object> map=new HashMap<String, Object>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		map.put("timestamp", sdf2.format(new java.util.Date()));
		map.put("statusCode", code);
		map.put("message", message);
		map.put("data", data);
		return map;
	}
}
