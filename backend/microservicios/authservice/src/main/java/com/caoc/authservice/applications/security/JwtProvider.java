package com.caoc.authservice.applications.security;

import com.caoc.authservice.domain.model.AuthUserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider{
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(secretBytes);
    }

    public String createToken(AuthUserDto authUser) throws JsonProcessingException {

        String username = authUser.getUsername();
        String email = authUser.getEmail();
        String id = authUser.getId();

        Collection<? extends GrantedAuthority> roles = authUser.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
        boolean isAdmin = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .add("isAdmin", isAdmin)
                .add("id", id)
                .add("email", email)
                .build();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .signWith(secretKey)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86_400_000L))
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUserNameFromToken(String token) {
        return getClaims(token).getSubject();
    }
}
