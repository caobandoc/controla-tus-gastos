package com.caoc.accountservice.domain.model.account;

import lombok.Data;

@Data
public class EUAccount {
    private String id;
    private String name;
    private String userId;
    private String typeAccountId;
    private Double amount;
    private String currencyId;
}
