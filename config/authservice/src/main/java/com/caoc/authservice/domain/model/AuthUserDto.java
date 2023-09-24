package com.caoc.authservice.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthUserDto {
    private String userName;
    private String password;
}
