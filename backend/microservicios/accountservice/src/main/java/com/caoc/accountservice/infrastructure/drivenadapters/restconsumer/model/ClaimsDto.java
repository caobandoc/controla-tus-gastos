package com.caoc.accountservice.infrastructure.drivenadapters.restconsumer.model;

import lombok.Data;

@Data
public class ClaimsDto {
    private String authorities;
    private Boolean isAdmin;
    private String id;
    private String email;
    private String sub;
}
