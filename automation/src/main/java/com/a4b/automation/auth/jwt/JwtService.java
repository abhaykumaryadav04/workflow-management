package com.a4b.automation.auth.jwt;



import java.util.Date;



import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                   .subject(userDetails.getUsername())
                   
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis()+expiration))
                   .signWith(getSigningKey())
                   .compact();
    }

    private SecretKey getSigningKey() {
      byte[] bytes=Decoders.BASE64.decode(secret);
      return Keys.hmacShaKeyFor(bytes);
    }
    public String extractUsername(String token){
     return extractAllClaims(token).getSubject();
    }
    private Claims  extractAllClaims(String token) {
       return Jwts.parser()
                  .verifyWith(getSigningKey())
                  .build()
                  .parseSignedClaims(token)
                .getPayload();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        String username=extractUsername(token);
        return  username.equals(userDetails.getUsername())&&isTokenExpired(token);
    }
    
    

}
