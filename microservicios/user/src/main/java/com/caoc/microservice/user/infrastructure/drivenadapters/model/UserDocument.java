package com.caoc.microservice.user.infrastructure.drivenadapters.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class UserDocument {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;

}
