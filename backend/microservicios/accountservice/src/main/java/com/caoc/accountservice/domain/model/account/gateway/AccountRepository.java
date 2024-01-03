package com.caoc.accountservice.domain.model.account.gateway;

import com.caoc.accountservice.domain.model.account.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {

    Mono<Account> save(Account account) ;

    Flux<Account> findAllByUserId(String userId);

    Mono<Account> findById(String id);
}
