package com.jetwise_airline.jwt_common;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiry-time-ms}")
    private long expiryTime;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiryTime))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }


    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }


    public List<String> getRoles(String token) {
        try {
            Claims claims =Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();

            Object rolesClaim = claims.get("roles");

            if (rolesClaim instanceof List<?>) {
                return ((List<?>) rolesClaim).stream()
                        .map(Object::toString)
                        .map(role -> "ROLE_" + role)
                        .collect(Collectors.toList());
            }
            return List.of();
        } catch (Exception e) {
            return List.of();
        }
    }
}



