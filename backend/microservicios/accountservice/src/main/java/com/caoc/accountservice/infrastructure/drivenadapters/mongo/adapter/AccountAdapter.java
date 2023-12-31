package com.caoc.accountservice.infrastructure.drivenadapters.mongo.adapter;

import com.caoc.accountservice.domain.model.account.Account;
import com.caoc.accountservice.domain.model.account.gateway.AccountRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.mongo.crud.AccountCrudRepository;
import com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents.AccountDto;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AccountAdapter implements AccountRepository {
    private final AccountCrudRepository accountCrudRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Account> save(Account account) {
        return accountCrudRepository.save(objectMapper.map(account, AccountDto.class))
                .map(obj -> objectMapper.map(obj, Account.class));
    }

    @Override
    public Flux<Account> findAllByUserId(String userId) {
        return accountCrudRepository.findAllByUserId(userId)
                .map(obj -> objectMapper.map(obj, Account.class));
    }
}
