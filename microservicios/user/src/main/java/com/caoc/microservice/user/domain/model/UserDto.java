package com.caoc.microservice.user.domain.model;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
}
