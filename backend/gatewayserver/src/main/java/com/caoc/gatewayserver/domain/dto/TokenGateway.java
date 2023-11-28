package com.caoc.gatewayserver.domain.dto;

import reactor.core.publisher.Mono;

public interface TokenGateway {
    Mono<TokenDto> validateToken(String token);
}
