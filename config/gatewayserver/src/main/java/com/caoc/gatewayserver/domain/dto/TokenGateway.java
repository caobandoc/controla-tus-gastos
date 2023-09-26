package com.caoc.gatewayserver.domain.dto;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface TokenGateway {
    Mono<TokenDto> validateToken(String token);
}
