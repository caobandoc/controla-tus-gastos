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
        return ServerResponse.ok().body(userUseCase.getAllUsers(), User.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        return ServerResponse.ok().body(userUseCase.getUserByEmail(serverRequest.pathVariable("id")), User.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(User.class)
                .flatMap(user -> ServerResponse.ok().body(userUseCase.save(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
