package com.caoc.authservice.domain.model.gateway;

import com.caoc.authservice.domain.model.AuthUserDto;
import reactor.core.publisher.Mono;

public interface AuthUserDtoGateway {

    Mono<AuthUserDto> findByUsername(String username);
}
