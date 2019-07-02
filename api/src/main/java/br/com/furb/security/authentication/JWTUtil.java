package br.com.furb.security.authentication;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean validToken(String token) {
		if(StringUtils.isNotBlank(token)) {
			Claims claims = getClaims(token);
			if(claims != null) {
				String username = claims.getSubject();
				Date expirationDate = claims.getExpiration();
				Date now = new Date(System.currentTimeMillis());
				
				if(StringUtils.isNotBlank(username) && expirationDate != null && now.before(expirationDate)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String getUsername(String token) {
		String username = null;
		if(StringUtils.isNotBlank(token)) {
			Claims claims = getClaims(token);
			if(claims != null) {
				username = claims.getSubject();
			}
		}
		
		return username;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(secret.getBytes())
					.parseClaimsJws(token)
					.getBody(); 
		} catch (Exception e) {
			return null;
		}
	}
}
