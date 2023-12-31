package com.caoc.accountservice.infrastructure.drivenadapters.restconsumer;

import com.caoc.accountservice.domain.model.claims.Claims;
import com.caoc.accountservice.domain.model.claims.gateway.ClaimsRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.restconsumer.model.ClaimsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestConsumer implements ClaimsRepository {
    private final WebClient client;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Claims> getClaims(String bearerToken) {
        return client.get()
                .uri("/claims")
                .header("Authorization", bearerToken)
                .retrieve()
                .bodyToMono(ClaimsDto.class)
                .map(this::map);
    }

    private Claims map(ClaimsDto claimsDto) {
        return objectMapper.map(claimsDto, Claims.class);
    }
}
