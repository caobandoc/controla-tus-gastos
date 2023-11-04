package com.caoc.authservice.infrastructure.drivenadapters.crud;

import com.caoc.authservice.infrastructure.drivenadapters.documents.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IRoleCrudRepository extends ReactiveMongoRepository<Role, String> {
}
