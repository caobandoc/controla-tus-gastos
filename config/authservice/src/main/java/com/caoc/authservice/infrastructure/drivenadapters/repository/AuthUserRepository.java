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

    public Mono<AuthUser> findByUserName(String userName) {
        return authUserCrudRepository.findByUserName(userName);
    }


    public Mono<AuthUser> save(AuthUser authUser) {
        return authUserCrudRepository.save(authUser);
    }

    public Mono<Boolean> existsByUserName(String userName) {
        return authUserCrudRepository.existsByUserName(userName);
    }
}
