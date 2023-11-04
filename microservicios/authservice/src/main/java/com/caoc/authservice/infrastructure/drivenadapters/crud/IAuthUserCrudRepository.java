package com.caoc.authservice.infrastructure.drivenadapters.crud;

import com.caoc.authservice.infrastructure.drivenadapters.documents.AuthUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IAuthUserCrudRepository extends ReactiveMongoRepository<AuthUser, String> {
    Mono<AuthUser> findByUsername(String username);
}
