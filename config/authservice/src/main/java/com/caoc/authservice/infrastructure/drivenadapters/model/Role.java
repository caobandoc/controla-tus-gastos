package com.caoc.authservice.infrastructure.drivenadapters.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "roles")
@Data
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -4430928776173397370L;
    private String name;

}
