package com.caoc.authservice.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    private String id;
    @NotBlank
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters long")
    private String username;
    private String email;
    @NotBlank
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;
    private Boolean enabled;
    private List<RoleDto> roles;
}
