package com.caoc.accountservice.domain.model.account.gateway;

import com.caoc.accountservice.domain.model.account.EUAccount;
import com.caoc.accountservice.domain.model.account.ResponseAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {

    Mono<ResponseAccount> save(EUAccount responseAccount) ;

    Flux<ResponseAccount> findAllByUserId(String userId);

    Mono<ResponseAccount> findById(String id);

    Mono<Void> deleteById(String id);
}
