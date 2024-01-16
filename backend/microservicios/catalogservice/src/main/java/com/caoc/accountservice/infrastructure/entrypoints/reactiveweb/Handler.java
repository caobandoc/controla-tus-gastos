package com.caoc.accountservice.infrastructure.entrypoints.reactiveweb;

import com.caoc.accountservice.domain.model.account.Catalog;
import com.caoc.accountservice.domain.usecase.CatalogUseCase;
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
    private final CatalogUseCase catalogUseCase;

    public Mono<ServerResponse> getListCatalog(ServerRequest serverRequest) {
        return catalogUseCase.getListCatalog()
                .collectList()
                .flatMap(listCatalog -> ServerResponse.ok().bodyValue(listCatalog));
    }

    public Mono<ServerResponse> getCatalog(ServerRequest serverRequest) {
        return catalogUseCase.getCatalog(serverRequest.pathVariable("catalog"))
                .collectList()
                .flatMap(listCatalog -> ServerResponse.ok().bodyValue(listCatalog));
    }

    public Mono<ServerResponse> createCatalog(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Catalog.class)
                .flatMap(catalogUseCase::createAccount)
                .flatMap(catalog -> ServerResponse.ok().bodyValue(catalog));
    }

    public Mono<ServerResponse> deleteAccount(ServerRequest serverRequest) {
        return catalogUseCase.deleteAccount(serverRequest.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

    public Mono<ServerResponse> getCatalogById(ServerRequest serverRequest) {
        return catalogUseCase.getCatalogById(serverRequest.pathVariable("id"))
                .flatMap(catalog -> ServerResponse.ok().bodyValue(catalog));
    }
}