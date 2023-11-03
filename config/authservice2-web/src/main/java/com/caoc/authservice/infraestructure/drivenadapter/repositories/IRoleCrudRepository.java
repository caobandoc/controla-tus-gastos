package com.caoc.authservice.infraestructure.drivenadapter.repositories;


import com.caoc.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRoleCrudRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
