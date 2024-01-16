package com.caoc.accountservice.infrastructure.drivenadapters.mongo.adapter;

import com.caoc.accountservice.domain.model.account.Catalog;
import com.caoc.accountservice.domain.model.account.gateway.CatalogRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.mongo.crud.CatalogCrudRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents.CatalogDto;
import com.caoc.accountservice.infrastructure.drivenadapters.mongo.services.CatalogService;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CatalogAdapter implements CatalogRepository {
    private final CatalogCrudRepository catalogCrudRepository;
    private final CatalogService catalogService;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<String> findNameCatalog() {
        return catalogService.getListCatalog();
    }

    @Override
    public Flux<Catalog> findByName(String catalog) {
        return catalogCrudRepository.findByName(catalog)
                .map(obj -> objectMapper.map(obj, Catalog.class));
    }

    @Override
    public Mono<Catalog> save(Catalog catalog) {
        return catalogCrudRepository.save(objectMapper.map(catalog, CatalogDto.class))
                .map(obj -> objectMapper.map(obj, Catalog.class));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return catalogCrudRepository.deleteById(id);
    }

    @Override
    public Mono<Catalog> findById(String id) {
        return catalogCrudRepository.findById(id)
                .map(obj -> objectMapper.map(obj, Catalog.class));
    }

}
