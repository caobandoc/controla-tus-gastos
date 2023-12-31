package com.caoc.accountservice.domain.model.account;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String name;
    private String userId;
    private TypeAccount typeAccount;
    private Double amount;
    private String currency;
}
