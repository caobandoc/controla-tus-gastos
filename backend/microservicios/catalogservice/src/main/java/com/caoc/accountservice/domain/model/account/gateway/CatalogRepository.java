package com.caoc.accountservice.domain.model.account.gateway;

import com.caoc.accountservice.domain.model.account.Catalog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogRepository {
    Flux<String> findNameCatalog();

    Mono<Catalog> save(Catalog catalog) ;

    Flux<Catalog> findByName(String name);

    Mono<Void> deleteById(String id);

    Mono<Catalog> findById(String id);
}
