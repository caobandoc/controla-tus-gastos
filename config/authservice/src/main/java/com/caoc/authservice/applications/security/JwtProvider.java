package com.caoc.authservice.applications.security;

import com.caoc.authservice.infrastructure.drivenadapters.model.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider{

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public String createToken(AuthUser authUser) {

        String username = authUser.getUsername();

        //Collection<? extends GrantedAuthority> roles = authUser.getAuthorities();
        //boolean isAdmin = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        Claims claims = Jwts.claims()
                //.add("authorities", new ObjectMapper().writeValueAsString(roles))
                //.add("isAdmin", isAdmin)
                .build();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86_400_000L))
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
