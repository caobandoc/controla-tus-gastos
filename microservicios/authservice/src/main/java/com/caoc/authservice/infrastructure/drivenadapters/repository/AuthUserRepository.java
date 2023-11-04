package com.caoc.authservice.infrastructure.drivenadapters.repository;

import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.domain.model.RoleDto;
import com.caoc.authservice.domain.model.gateway.AuthUserDtoGateway;
import com.caoc.authservice.infrastructure.drivenadapters.crud.IAuthUserCrudRepository;
import com.caoc.authservice.infrastructure.drivenadapters.crud.IRoleCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthUserRepository implements AuthUserDtoGateway {
    private final IAuthUserCrudRepository authUserCrudRepository;
    private final IRoleCrudRepository roleCrudRepository;
    private final ObjectMapper mapper;


    public Mono<AuthUserDto> findByUsername(String username) {
        return authUserCrudRepository.findByUsername(username)
                .flatMap(authUser -> getRoles(authUser.getRoles())
                        .map(roleDtos -> {
                            AuthUserDto authUserDto = mapper.map(authUser, AuthUserDto.class);
                            authUserDto.setRoles(roleDtos);
                            return authUserDto;
                        })
                );
    }


    private Mono<List<RoleDto>> getRoles(List<String> roles) {
        return roleCrudRepository.findAllById(roles)
                .map(role -> mapper.map(role, RoleDto.class))
                .collectList();
    }

}
