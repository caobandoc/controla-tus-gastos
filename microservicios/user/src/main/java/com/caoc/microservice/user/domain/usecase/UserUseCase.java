package com.caoc.microservice.user.domain.usecase;

import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.infrastructure.drivenadapters.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserDto> save(UserDto userDto) {
        return userRepository.save(userDto);
    }
}
