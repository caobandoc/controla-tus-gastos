package com.caoc.microservice.user.infrastructure.drivenadapters.crud;

import com.caoc.documents.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IRoleCrudRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findByName(String name);
}
