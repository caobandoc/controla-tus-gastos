package com.caoc.microservice.user.domain.usecase;

import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.domain.model.UserResponseDto;
import com.caoc.microservice.user.infrastructure.drivenadapters.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserResponseDto> save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto)
                .map(user -> UserResponseDto.builder()
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build());
    }
}
