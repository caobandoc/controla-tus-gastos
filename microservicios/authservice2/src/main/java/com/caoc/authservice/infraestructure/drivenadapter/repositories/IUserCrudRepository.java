package com.caoc.authservice.infraestructure.drivenadapter.repositories;

import com.caoc.authservice.infraestructure.drivenadapter.documents.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserCrudRepository extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);

}
