package com.caoc.authservice.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    @NotBlank(message = "El campo 'nombre' no puede estar en blanco")
    @Size(max = 50, message = "El campo 'nombre' no debe tener más de 50 caracteres")
    private String username;
    @NotBlank(message = "El campo 'password' no puede estar en blanco")
    private String password;
}
