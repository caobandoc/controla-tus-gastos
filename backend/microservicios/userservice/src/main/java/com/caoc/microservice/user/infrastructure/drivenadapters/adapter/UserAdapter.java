package com.caoc.microservice.user.infrastructure.drivenadapters.adapter;

import com.caoc.microservice.user.domain.model.UserDto;
import com.caoc.microservice.user.infrastructure.drivenadapters.crud.IRoleCrudRepository;
import com.caoc.microservice.user.infrastructure.drivenadapters.crud.IUserCrudRepository;
import com.caoc.microservice.user.infrastructure.drivenadapters.documents.AuthUser;
import com.caoc.microservice.user.infrastructure.drivenadapters.documents.Role;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserAdapter {
    private final IUserCrudRepository iUserCrudRepository;
    private final IRoleCrudRepository iRoleCrudRepository;
    private final ObjectMapper objectMapper;

    public Flux<UserDto> findAll() {
        return iUserCrudRepository.findAll().map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }

    public Mono<UserDto> findByEmail(String email) {
        return iUserCrudRepository.findByEmail(email).map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }

    public Mono<UserDto> save(UserDto userDto) {
        return Mono.just(userDto)
                .map(userDto1 -> objectMapper.map(userDto1, AuthUser.class))
                .flatMap(newUser ->iRoleCrudRepository.findByName("ROLE_USER")
                        .map(role -> {
                            List<Role> roles = new ArrayList<>();
                            roles.add(role);
                            newUser.setRoles(roles);
                            newUser.setEnabled(true);
                            return newUser;
                        }))
                .flatMap(iUserCrudRepository::save)
                .map(userEntity -> objectMapper.map(userEntity, UserDto.class));
    }
}
