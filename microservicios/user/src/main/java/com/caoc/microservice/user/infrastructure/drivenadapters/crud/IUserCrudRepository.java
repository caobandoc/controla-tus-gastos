package com.caoc.microservice.user.infrastructure.drivenadapters.crud;

import com.caoc.microservice.user.infrastructure.drivenadapters.model.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IUserCrudRepository extends ReactiveMongoRepository<UserDocument, String> {

    Mono<UserDocument> findByEmail(String email);
}
