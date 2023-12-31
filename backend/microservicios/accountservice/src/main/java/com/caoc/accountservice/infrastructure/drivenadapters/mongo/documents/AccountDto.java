package com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
public class AccountDto {
    private String id;
    private String name;
    private String userId;
    private TypeAccount typeAccount;
    private Double amount;
    private String currency;
}
