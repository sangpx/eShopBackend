package com.base.springsecurity.security.jwt;

import java.security.Key;
import java.util.Date;

import com.base.springsecurity.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//JwtUtils cung cấp các phương thức tạo, phân tích cú pháp, xác thực JWT
@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${ecommerce.app.jwtSecret}")
  private String jwtSecret;

  @Value("${ecommerce.app.jwtExpirationMs}")
  private int jwtExpirationMs; // Thoi gian het han Token

  //Tu tong tin User -> tao JWT
  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    //Tao chuoi JWT tu userName
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername())) //tao JWT tu User
        .setIssuedAt(new Date()) // Ngay Tao
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Ngay Het Han
        .signWith(key(), SignatureAlgorithm.HS256) // Thuat toan ma hoa
        .compact(); //generate ra token
  }
  
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }


  //Tu JWT -> lay ra thong tin User
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  //validateJwtToken
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
}
