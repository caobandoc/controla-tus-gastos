package com.caoc.authservice.applications.auth;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtProvider {
    public static final String PREFIX_TOKEN = "Bearer ";
    @Value("${jwt.secret}")
    private String secret;
    @Getter
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(secretBytes);
    }

}
