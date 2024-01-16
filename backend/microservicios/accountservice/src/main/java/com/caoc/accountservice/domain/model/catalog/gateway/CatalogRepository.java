package com.caoc.accountservice.domain.model.catalog.gateway;

import com.caoc.accountservice.domain.model.catalog.Catalog;
import reactor.core.publisher.Mono;

public interface CatalogRepository {

    Mono<Catalog> getCatalogById(String id);
}
