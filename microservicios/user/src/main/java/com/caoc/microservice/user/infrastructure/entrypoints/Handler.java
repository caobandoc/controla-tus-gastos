package com.caoc.microservice.user.infrastructure.entrypoints;

import com.caoc.exception.RequestValidationException;
import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {
    private final UserUseCase userUseCase;
    private final Validator validator;

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return userUseCase.getAllUsers()
                .collectList()
                .flatMap(users -> ServerResponse.ok().bodyValue(users))
                .switchIfEmpty(ServerResponse.notFound().build())
                .doOnNext(users -> log.info("Users: {}", users));
    }

    public Mono<ServerResponse> getUserByEmail(ServerRequest serverRequest) {
        return userUseCase.getUserByEmail(serverRequest.pathVariable("email"))
                .flatMap(userDto -> ServerResponse.ok().bodyValue(userDto))
                .switchIfEmpty(ServerResponse.notFound().build())
                .doOnNext(user -> log.info("User: {}", user));
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDto.class)
                .map(dto -> {
                    Errors errors = new BeanPropertyBindingResult(dto, UserDto.class.getName());
                    validator.validate(dto, errors);
                    if (errors.hasErrors())
                        throw new RequestValidationException(errors.getAllErrors().getFirst().getDefaultMessage());
                    return dto;
                })
                .flatMap(userUseCase::save)
                .flatMap(userDtoSaved -> ServerResponse.ok().bodyValue(userDtoSaved))
                .switchIfEmpty(ServerResponse.badRequest().build())
                .onErrorResume(this::handleException);
    }

    private Mono<ServerResponse> handleException(Throwable error) {
        return switch (error.getClass().getSimpleName()) {
            case "DuplicateKeyException" -> ServerResponse
                    .status(HttpStatus.CONFLICT)
                    .bodyValue(ProblemDetail
                            .forStatusAndDetail(HttpStatus.CONFLICT,
                                    "Username or Email already exists"));
            default -> ServerResponse
                    .badRequest()
                    .bodyValue(ProblemDetail
                            .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                                    error.getMessage()));
        };
    }
}