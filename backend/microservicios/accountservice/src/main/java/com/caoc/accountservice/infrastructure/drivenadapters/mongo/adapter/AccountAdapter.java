package com.caoc.accountservice.infrastructure.drivenadapters.mongo.adapter;

import com.caoc.accountservice.domain.model.account.EUAccount;
import com.caoc.accountservice.domain.model.account.ResponseAccount;
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
    public Mono<ResponseAccount> save(EUAccount responseAccount) {
        return accountCrudRepository.save(objectMapper.map(responseAccount, AccountDto.class))
                .map(obj -> objectMapper.map(obj, ResponseAccount.class));
    }

    @Override
    public Flux<ResponseAccount> findAllByUserId(String userId) {
        return accountCrudRepository.findAllByUserId(userId)
                .map(obj -> objectMapper.map(obj, ResponseAccount.class));
    }

    @Override
    public Mono<ResponseAccount> findById(String id) {
        return accountCrudRepository.findById(id)
                .map(obj -> objectMapper.map(obj, ResponseAccount.class));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return accountCrudRepository.deleteById(id);
    }

}
