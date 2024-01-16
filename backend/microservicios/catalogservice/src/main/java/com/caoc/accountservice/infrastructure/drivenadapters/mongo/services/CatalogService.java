package com.caoc.accountservice.infrastructure.drivenadapters.mongo.services;

import com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents.CatalogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<String> getListCatalog() {
        return mongoTemplate.findDistinct(new Query(), "name", CatalogDto.class, String.class);
    }
}
