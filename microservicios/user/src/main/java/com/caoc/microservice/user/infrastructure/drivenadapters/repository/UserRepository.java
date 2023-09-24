package com.caoc.microservice.user.infrastructure.drivenadapters.repository;

import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.infrastructure.drivenadapters.crud.IUserCrudRepository;
import com.caoc.microservice.user.infrastructure.drivenadapters.model.UserDocument;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final ObjectMapper objectMapper;

    public Flux<UserDto> findAll() {
        return iUserCrudRepository.findAll().map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }

    public Mono<UserDto> findByEmail(String email) {
        return iUserCrudRepository.findByEmail(email).map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }

    public Mono<UserDto> save(UserDto userDto) {
        return Mono.just(userDto)
                .map(userDto1 -> {
                    UserDocument newUser = objectMapper.map(userDto1, UserDocument.class);
                    newUser.setId(UUID.randomUUID().toString());
                    return newUser;
                })
                .flatMap(iUserCrudRepository::save)
                .map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }
}
