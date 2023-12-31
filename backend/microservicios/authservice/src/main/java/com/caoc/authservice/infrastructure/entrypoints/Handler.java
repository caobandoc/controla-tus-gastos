package com.caoc.authservice.infrastructure.entrypoints;

import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.domain.usecase.AuthUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {

    private final AuthUserUseCase authUserUseCase;
    private final Validator validator;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request
                .bodyToMono(AuthUserDto.class)
                .map(dto -> {
                    Errors errors = new BeanPropertyBindingResult(dto, AuthUserDto.class.getName());
                    validator.validate(dto, errors);
                    if (errors.hasErrors())
                        throw new RuntimeException(errors.getAllErrors().getFirst().getDefaultMessage());
                    return dto;
                })
                .flatMap(authUserUseCase::login)
                .flatMap(tokenDto -> ServerResponse.ok().bodyValue(tokenDto))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }
    public Mono<ServerResponse> validate(ServerRequest request) {
        return authUserUseCase.validate(request.headers()
                        .header(HttpHeaders.AUTHORIZATION).get(0).replace("Bearer ", ""))
                .flatMap(tokenDto -> ServerResponse.ok().bodyValue(tokenDto))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

    public Mono<ServerResponse> claims(ServerRequest serverRequest) {
        return authUserUseCase.claims(serverRequest.headers()
                        .header(HttpHeaders.AUTHORIZATION).getFirst().replace("Bearer ", ""))
                .flatMap(claims -> ServerResponse.ok().bodyValue(claims))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }
}
