package com.caoc.authservice.infrastructure.drivenadapters.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -4430928776173397370L;
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

}
