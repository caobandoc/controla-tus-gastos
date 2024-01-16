package com.caoc.accountservice.infrastructure.drivenadapters.restconsumer;

import com.caoc.accountservice.domain.model.catalog.Catalog;
import com.caoc.accountservice.domain.model.catalog.gateway.CatalogRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.restconsumer.model.CatalogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestConsumer implements CatalogRepository {
    private final WebClient client;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Catalog> getCatalogById(String id) {
        log.info("getCatalogById: {}", id);
        return client.get()
                .uri("/id/{id}", id)
                .retrieve()
                .bodyToMono(CatalogDto.class)
                .map(this::map);
    }

    private Catalog map(CatalogDto catalogDto) {
        return objectMapper.map(catalogDto, Catalog.class);
    }

}
