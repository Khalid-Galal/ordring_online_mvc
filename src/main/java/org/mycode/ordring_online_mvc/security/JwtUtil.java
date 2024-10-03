package org.mycode.ordring_online_mvc.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class JwtUtil {
    private String secretKey = "yourSecretKey";
    // Generate token with user details
    public String generateToken(Long userId, String username, Boolean isAdmin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("isAdmin", 1);

        return Jwts.builder()
                .setClaims(claims)  // Attach user data as claims
                .setSubject(username)  // The subject of the token (typically the user)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Token expiry time (10 hours)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Sign the token with the secret key
                .compact();  // Create and compact the JWT into a string
    }
    // Extract claims from token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)  // Use the same secret key to parse the token
                .parseClaimsJws(token)
                .getBody();  // Return the claims (user data) from the token
    }
    // Get individual fields from token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();  // Extract the username
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);  // Extract the user ID
    }

    public Boolean extractIsAdmin(String token) {
        return extractAllClaims(token).get("isAdmin", Boolean.class);  // Extract admin status
    }
    // Check if the token is valid
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
