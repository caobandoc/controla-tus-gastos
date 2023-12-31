package com.caoc.accountservice.infrastructure.entrypoints.reactiveweb;

import com.caoc.accountservice.domain.model.account.Account;
import com.caoc.accountservice.domain.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {
    private final Validator validator;
    private final AccountUseCase accountUseCase;

    public Mono<ServerResponse> getAllAccounts(ServerRequest serverRequest) {
        String bearerToken = serverRequest.headers().header("Authorization").getFirst();
        return accountUseCase.getAllAccountsByUserId(bearerToken)
                .collectList()
                .flatMap(accounts -> ServerResponse.ok().bodyValue(accounts))
                .onErrorResume(error -> {
                    log.error("Error getting all accounts", error);
                    return ServerResponse.badRequest().bodyValue(error.getMessage());
                });

    }

    public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {
        String bearerToken = serverRequest.headers().header("Authorization").getFirst();
        return serverRequest.bodyToMono(Account.class)
                .flatMap(account -> accountUseCase.createAccount(account, bearerToken))
                .flatMap(account -> ServerResponse.ok().bodyValue(account))
                .onErrorResume(error -> {
                    log.error("Error creating account", error);
                    return ServerResponse.badRequest().bodyValue(error.getMessage());
                });
    }
}