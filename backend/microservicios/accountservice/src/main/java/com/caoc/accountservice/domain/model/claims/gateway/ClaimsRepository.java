package com.caoc.accountservice.domain.model.claims.gateway;

import com.caoc.accountservice.domain.model.claims.Claims;
import reactor.core.publisher.Mono;

public interface ClaimsRepository {

    Mono<Claims> getClaims( String bearerToken) ;
}
