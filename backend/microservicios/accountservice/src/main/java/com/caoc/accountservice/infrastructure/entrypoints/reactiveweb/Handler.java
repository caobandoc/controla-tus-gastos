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
        return accountUseCase.getAllAccountsByUserId(serverRequest.pathVariable("userId"))
                .collectList()
                .flatMap(accounts -> ServerResponse.ok().bodyValue(accounts));
    }

    public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Account.class)
                .flatMap(accountUseCase::createAccount)
                .flatMap(account -> ServerResponse.ok().bodyValue(account));
    }

    public Mono<ServerResponse> updateAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Account.class)
                .flatMap(accountUseCase::updateAccount)
                .flatMap(account -> ServerResponse.ok().bodyValue(account));
    }

    public Mono<ServerResponse> deleteAccount(ServerRequest serverRequest) {
        return accountUseCase.deleteAccount(serverRequest.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }
}