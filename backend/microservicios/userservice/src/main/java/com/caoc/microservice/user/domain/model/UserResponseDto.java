package com.caoc.microservice.user.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String username;
    private String email;
}
