package com.example.main.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.main.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private final String JWT_SECRET = "hollow";
	private final long JWT_EXPIRATION = 604800000L;

	public String generateToken(User user) {
		Date now = new Date();
		Date expire = new Date(now.getTime() + JWT_EXPIRATION);
	
		return Jwts.builder().setSubject(user.getUser_id()+"").setIssuedAt(now).setExpiration(expire)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}
	
	public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }
	public boolean validateToken(String authToken) {
    	try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.print("Invalid JWT");
        } catch (ExpiredJwtException ex) {
        	System.out.print("Expired JWT");
        } catch (UnsupportedJwtException ex) {
        	System.out.print("Unsupported JWT");
        } catch (IllegalArgumentException ex) {
        	System.out.print("JWT claims string is empty.");
        }
        return false;
    }
}
