package com.caoc.accountservice.domain.model.account;

import com.caoc.accountservice.domain.model.currenct.Currency;
import com.caoc.accountservice.domain.model.typeaccount.TypeAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ResponseAccount {
    private String id;
    private String name;
    private String userId;
    @JsonIgnore
    private String typeAccountId;
    private TypeAccount typeAccount;
    private Double amount;
    @JsonIgnore
    private String currencyId;
    private Currency currency;
}
