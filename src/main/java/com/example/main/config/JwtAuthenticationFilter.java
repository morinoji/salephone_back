package com.example.main.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.main.jwt.JwtTokenProvider;
import com.example.main.models.user;
import com.example.main.services.UserService;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtProvider;
	@Autowired
	private UserService userService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
			String jwt=getJwtFromRequest(request);

				if(StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
					
					int id=Integer.parseInt(jwtProvider.getUserIdFromJWT(jwt));
					user userData= userService.getUserById(id);

					if(userData != null) {

	                    UsernamePasswordAuthenticationToken
	                            authentication = new UsernamePasswordAuthenticationToken(userData, null, userData.getAuthorities());
	                    SecurityContextHolder.getContext().setAuthentication(authentication);
	                }
				}
		} catch (Exception e) {
			
		}
		filterChain.doFilter(request, response);
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
            
        }
        return null;
    }
	
	private boolean shouldFilter(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/authen");
	}
}
