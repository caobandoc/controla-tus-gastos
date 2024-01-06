package com.caoc.accountservice.domain.usecase;

import com.caoc.accountservice.domain.model.account.Account;
import com.caoc.accountservice.domain.model.account.gateway.AccountRepository;
import com.caoc.accountservice.domain.model.claims.gateway.ClaimsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountUseCase {
    private final AccountRepository accountRepository;

    public Flux<Account> getAllAccountsByUserId(String userId) {
        return accountRepository.findAllByUserId(userId);
    }

    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Account> updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }
}
