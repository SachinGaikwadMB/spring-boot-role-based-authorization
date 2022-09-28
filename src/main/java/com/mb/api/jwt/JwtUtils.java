package com.mb.api.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils
{
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);
	@Value("${app.jwtSecretKey}")
	private String jwtSecretKey;

	@Value("${app.jwtExpirationsMs}")
	private int jwtExpirationsMs;

	// Generate Jwt Token
	public String generateJwtToken(String username)
	{
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationsMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
	}

	// Parse Jwt Token
	public Claims parseJwtToken(String token)
	{
		return Jwts.parser()
				.setSigningKey(jwtSecretKey)
				.parseClaimsJws(token)
				.getBody();
	}

	// Get Username from Jwt Token
	public String getSubjectFromJwtToken(String token)
	{
		return parseJwtToken(token).getSubject();

	}

	// validate token
	public boolean validateJwtToken(String token)
	{

		try
		{
			parseJwtToken(token).getExpiration().after(new Date(System.currentTimeMillis()));
			return true;
		}
		catch (SignatureException ex)
		{
			logger.error("Invalid Signature{}", ex.getMessage());

		}
		catch (MalformedJwtException ex)
		{
			logger.error("Malformed Exception{}", ex.getMessage());
		}
		catch (UnsupportedJwtException ex)
		{
			logger.error("Unsupported exception{}", ex.getMessage());
		}
		return false;

	}

}
