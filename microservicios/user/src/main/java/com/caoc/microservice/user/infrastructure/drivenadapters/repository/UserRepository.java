package com.caoc.microservice.user.infrastructure.drivenadapters.repository;

import com.caoc.microservice.user.domain.model.User;
import com.caoc.microservice.user.infrastructure.drivenadapters.crud.IUserCrudRepository;
import com.caoc.microservice.user.infrastructure.drivenadapters.model.UserDocument;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final ObjectMapper objectMapper;

    public Flux<User> findAll() {
        return iUserCrudRepository.findAll().map(userEntity -> objectMapper.map(userEntity, User.class));
    }

    public Mono<User> findByEmail(String email) {
        return iUserCrudRepository.findByEmail(email).map(userEntity -> objectMapper.map(userEntity, User.class));
    }

    public Mono<User> save(User user) {
        return iUserCrudRepository.save(objectMapper.map(user, UserDocument.class))
                .map(userEntity -> objectMapper.map(userEntity, User.class));
    }
}
