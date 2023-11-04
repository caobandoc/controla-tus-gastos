package com.caoc.microservice.user.infrastructure.drivenadapters.crud;

import com.caoc.microservice.user.infrastructure.drivenadapters.documents.AuthUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IUserCrudRepository extends ReactiveMongoRepository<AuthUser, String> {

    Mono<AuthUser> findByEmail(String email);
}
