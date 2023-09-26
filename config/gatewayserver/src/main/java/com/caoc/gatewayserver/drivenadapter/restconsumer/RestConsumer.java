package com.caoc.gatewayserver.drivenadapter.restconsumer;

import com.caoc.gatewayserver.domain.dto.TokenDto;
import com.caoc.gatewayserver.domain.dto.TokenGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestConsumer implements TokenGateway {
    private final WebClient webClient;
    @Override
    public Mono<TokenDto> validateToken(String token) {
        return webClient.post()
                .uri("lb://auth-service/api/v1/auth/validate?token=" + token)
                .retrieve()
                .bodyToMono(TokenDto.class);
    }
}
