package main.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "super-secret-key-for-online-library-web-application";
    private static final Long EXPIRATION_MS = 1000L * 60 *60 * 24;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(String email){
        Date now = new Date();
        Date after = new Date(now.getTime()+EXPIRATION_MS);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(after)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
