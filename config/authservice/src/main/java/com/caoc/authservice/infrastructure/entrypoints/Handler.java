package com.caoc.authservice.infrastructure.entrypoints;

import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.domain.usecase.AuthUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {

    private final AuthUserUseCase authUserUseCase;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request
                .bodyToMono(AuthUserDto.class)
                .flatMap(authUserUseCase::login)
                .flatMap(tokenDto -> ServerResponse.ok().bodyValue(tokenDto))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }
    public Mono<ServerResponse> validate(ServerRequest request) {
        return authUserUseCase.validate(request.pathVariable("token"))
                .flatMap(tokenDto -> ServerResponse.ok().bodyValue(tokenDto))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request
                .bodyToMono(AuthUserDto.class)
                .flatMap(authUserUseCase::save)
                .flatMap(authUserDto -> ServerResponse.ok().bodyValue(authUserDto))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

}