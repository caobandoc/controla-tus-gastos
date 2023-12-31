package com.caoc.accountservice.domain.model.claims;

import lombok.Data;

@Data
public class Claims {
    private String id;
    private String sub;
    private String email;
}
