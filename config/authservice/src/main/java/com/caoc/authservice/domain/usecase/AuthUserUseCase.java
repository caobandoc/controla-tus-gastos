package com.caoc.authservice.domain.usecase;

import com.caoc.authservice.applications.security.JwtProvider;
import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.domain.model.TokenDto;
import com.caoc.authservice.infrastructure.drivenadapters.model.AuthUser;
import com.caoc.authservice.infrastructure.drivenadapters.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthUserUseCase {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    public Mono<AuthUserDto> save(AuthUserDto dto){
        return authUserRepository.existsByUserName(dto.getUserName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("User already exists"));
                    }
                    return Mono.just(dto);
                })
                .map(dto1 -> AuthUser.builder()
                        .userName(dto1.getUserName())
                        .password(passwordEncoder.encode(dto1.getPassword()))
                        .build())
                .flatMap(authUserRepository::save)
                .map(authUserDto -> objectMapper.map(authUserDto, AuthUserDto.class));
    }

    public Mono<TokenDto> login(AuthUserDto dto){
        return authUserRepository.findByUserName(dto.getUserName())
                .map(authUser -> {
                    if (passwordEncoder.matches(dto.getPassword(), authUser.getPassword()))
                        return TokenDto.builder()
                                .token(jwtProvider.createToken(authUser))
                                .build();
                    return null;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User or Password incorrect")));
    }

    public Mono<TokenDto> validate(String token){
        return Mono.just(token)
                .filter(jwtProvider::validate)
                .map(jwtProvider::getUSerNameFromToken)
                .flatMap(authUserRepository::findByUserName)
                .map(authUser -> TokenDto.builder()
                        .token(token)
                        .build())
                .switchIfEmpty(Mono.error(new RuntimeException("Token invalid")));
    }



}
