package com.caoc.authservice.infrastructure.drivenadapters.repository;

import com.caoc.authservice.infrastructure.drivenadapters.crud.IAuthUserCrudRepository;
import com.caoc.authservice.infrastructure.drivenadapters.model.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AuthUserRepository {
    private final IAuthUserCrudRepository authUserCrudRepository;

    public Mono<AuthUser> findByUsername(String username) {
        return authUserCrudRepository.findByUsername(username);
    }

}
