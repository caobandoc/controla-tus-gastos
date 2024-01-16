package com.caoc.accountservice.infrastructure.drivenadapters.mongo.crud;

import com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents.CatalogDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CatalogCrudRepository extends ReactiveMongoRepository<CatalogDto, String> {

    Flux<CatalogDto> findByName(String catalog);

}
