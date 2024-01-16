package com.caoc.accountservice.domain.usecase;

import com.caoc.accountservice.domain.model.account.Catalog;
import com.caoc.accountservice.domain.model.account.gateway.CatalogRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CatalogUseCase {
    private final CatalogRepository catalogRepository;

    public Flux<String>getListCatalog() {
        return catalogRepository.findNameCatalog();
    }

    public Flux<Catalog> getCatalog(String catalog) {
        return catalogRepository.findByName(catalog);
    }

    public Mono<Catalog> createAccount(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    public Mono<Void> deleteAccount(String id) {
        return catalogRepository.deleteById(id);
    }


    public Mono<Catalog> getCatalogById(String id) {
        return catalogRepository.findById(id);
    }
}
