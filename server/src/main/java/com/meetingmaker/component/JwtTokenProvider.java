package com.meetingmaker.component;

import com.meetingmaker.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider implements Serializable {

    private static final String PREFIX = "Bearer ";

    public long JWT_TOKEN_VALIDITY =  3600;

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public String generateJwtToken(User user) {
        System.out.println(getAllClaimsFromToken(Jwts.builder()
                .setSubject(user.getEmail())
                .claim("authorities", "ROLE_USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact()));
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("authorities", "ROLE_USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean checkJWTToken(String header) {
        return header != null && header.startsWith(PREFIX);
    }

    public String getToken(String header) {
        return header.replace(PREFIX, "");
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // validate token
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
