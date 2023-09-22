package com.caoc.microservice.user.domain.usecase;

import com.caoc.microservice.user.domain.model.User;
import com.caoc.microservice.user.infrastructure.drivenadapters.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
