package com.caoc.accountservice.infrastructure.drivenadapters.mongo.crud;

import com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents.AccountDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AccountCrudRepository extends ReactiveMongoRepository<AccountDto, String> {
    Flux<AccountDto> findAllByUserId(String userId);
}
