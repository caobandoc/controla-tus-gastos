package com.caoc.microservice.user.domain.usecase;

import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.domain.model.UserResponseDto;
import com.caoc.microservice.user.infrastructure.drivenadapters.adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserAdapter userAdapter;
    private final PasswordEncoder passwordEncoder;
    public Flux<UserDto> getAllUsers() {
        return userAdapter.findAll();
    }

    public Mono<UserDto> getUserByEmail(String email) {
        return userAdapter.findByEmail(email);
    }

    public Mono<UserResponseDto> save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userAdapter.save(userDto)
                .map(user -> UserResponseDto.builder()
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build());
    }
}
