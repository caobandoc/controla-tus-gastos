package com.caoc.microservice.user.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @Size(min = 4, max = 50, message = "username must be between 4 and 50 characters")
    @NotBlank(message = "username is required")
    private String username;
    @Email(message = "email is not valid")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6,message = "password must be at least 6 characters")
    private String password;
}
