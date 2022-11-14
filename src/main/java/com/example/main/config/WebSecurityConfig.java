package com.example.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.main.services.UserService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	
	 UserService userService;
	 JwtAuthenticationFilter filter;

	 
	 public WebSecurityConfig(UserService userService, JwtAuthenticationFilter filter) {
		this.userService = userService;
		this.filter = filter;
	}


	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 @Bean
	    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder.build();
	    }
	 //hasAuthority("CUSTOMER")
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http      
		 .cors().and()
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/addToCart", "/getCartById","/changePassword","/updateCustomer","/postComment","/getOrdersById","/placeOrder","/update-avatar" ).permitAll();     
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	 
	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList(constant.client1));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/*", configuration);
	        return source;
	    }
	
}
