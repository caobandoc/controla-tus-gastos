package com.caoc.accountservice.domain.usecase;

import com.caoc.accountservice.domain.model.account.EUAccount;
import com.caoc.accountservice.domain.model.account.ResponseAccount;
import com.caoc.accountservice.domain.model.account.gateway.AccountRepository;
import com.caoc.accountservice.domain.model.catalog.gateway.CatalogRepository;
import com.caoc.accountservice.domain.model.currenct.Currency;
import com.caoc.accountservice.domain.model.typeaccount.TypeAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountUseCase {
    private final AccountRepository accountRepository;
    private final CatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    public Flux<ResponseAccount> getAllAccountsByUserId(String userId) {
        return accountRepository.findAllByUserId(userId)
                .flatMap(this::loadCatalog);
    }

    @Transactional
    public Mono<ResponseAccount> createAccount(EUAccount responseAccount) {
        return accountRepository.save(responseAccount)
                .flatMap(this::loadCatalog);
    }

    @Transactional
    public Mono<ResponseAccount> updateAccount(EUAccount responseAccount) {
        return accountRepository.save(responseAccount)
                .flatMap(this::loadCatalog);
    }

    @Transactional
    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }

    private Mono<ResponseAccount> loadCatalog(ResponseAccount account) {
        return catalogRepository.getCatalogById(account.getTypeAccountId())
                .map(cat -> {
                    account.setTypeAccount(TypeAccount.builder()
                            .id(cat.getId())
                            .name(cat.getValue())
                            .build());
                    return account;
                })
                .flatMap(obj ->
                        catalogRepository.getCatalogById(obj.getCurrencyId())
                                .map(cat -> {
                                    obj.setCurrency(Currency.builder()
                                            .id(cat.getId())
                                            .name(cat.getValue())
                                            .build());
                                    return obj;
                                })
                );
    }
}
