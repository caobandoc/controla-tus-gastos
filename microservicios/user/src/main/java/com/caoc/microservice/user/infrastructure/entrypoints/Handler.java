package com.caoc.microservice.user.infrastructure.entrypoints;

import com.caoc.microservice.user.domain.model.User;
import com.caoc.microservice.user.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final UserUseCase userUseCase;
    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return userUseCase.getAllUsers()
                .collectList()
                .flatMap(users -> ServerResponse.ok().bodyValue(users))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUserByEmail(ServerRequest serverRequest) {
        return userUseCase.getUserByEmail(serverRequest.pathVariable("email"))
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .flatMap(userUseCase::save)
                .flatMap(userSaved -> ServerResponse.ok().bodyValue(userSaved))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
