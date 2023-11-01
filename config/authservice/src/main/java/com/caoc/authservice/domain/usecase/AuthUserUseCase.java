package com.caoc.authservice.domain.usecase;

import com.caoc.authservice.applications.security.JwtProvider;
import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.domain.model.TokenDto;
import com.caoc.authservice.infrastructure.drivenadapters.repository.AuthUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthUserUseCase {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Mono<TokenDto> login(AuthUserDto dto){
        return authUserRepository.findByUsername(dto.getUsername())
                .map(authUser -> {
                    if (passwordEncoder.matches(dto.getPassword(), authUser.getPassword())) {
                        try {
                            return TokenDto.builder()
                                    .token(jwtProvider.createToken(authUser))
                                    .build();
                        } catch (JsonProcessingException e) {
                            return null;
                        }
                    }
                    return null;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User or Password incorrect")));
    }

    public Mono<TokenDto> validate(String token){
        return Mono.just(token)
                .filter(jwtProvider::validate)
                .map(jwtProvider::getUserNameFromToken)
                .flatMap(authUserRepository::findByUsername)
                .map(authUser -> TokenDto.builder()
                        .token(token)
                        .build())
                .switchIfEmpty(Mono.error(new RuntimeException("Token invalid")));
    }



}
