package com.caoc.authservice.infraestructure.drivenadapter.repositories;


import com.caoc.authservice.infraestructure.drivenadapter.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRoleCrudRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);
}
