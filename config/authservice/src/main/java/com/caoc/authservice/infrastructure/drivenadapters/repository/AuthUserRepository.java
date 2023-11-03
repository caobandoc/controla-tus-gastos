package com.caoc.authservice.infrastructure.drivenadapters.repository;

import com.caoc.authservice.domain.model.AuthUserDto;
import com.caoc.authservice.infrastructure.drivenadapters.crud.IAuthUserCrudRepository;
import com.caoc.documents.AuthUser;
import com.caoc.documents.Role;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AuthUserRepository {
    private final IAuthUserCrudRepository authUserCrudRepository;
    private final ObjectMapper mapper;

    public Mono<AuthUserDto> findByUsername(String username) {
        return authUserCrudRepository.findByUsername(username)
                .map(obj -> mapper.map(obj, AuthUserDto.class));
    }


}
