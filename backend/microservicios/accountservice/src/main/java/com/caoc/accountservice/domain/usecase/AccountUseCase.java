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
    private final ClaimsRepository claimsRepository;

    public Flux<Account> getAllAccountsByUserId(String bearerToken) {
        return claimsRepository.getClaims(bearerToken)
                .flatMapMany(claims -> accountRepository.findAllByUserId(claims.getId()));
    }

    public Mono<Account> createAccount(Account account, String bearerToken) {
        return claimsRepository.getClaims(bearerToken)
                .flatMap(claims -> {
                    account.setUserId(claims.getId());
                    return accountRepository.save(account);
                });
    }

    public Mono<Account> updateAccount(Account account) {
        return accountRepository.findById(account.getId())
                .flatMap(account1 -> accountRepository.save(account));
    }
}
