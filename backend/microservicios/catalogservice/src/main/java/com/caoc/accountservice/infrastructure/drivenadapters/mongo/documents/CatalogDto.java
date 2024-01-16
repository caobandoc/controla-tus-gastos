package com.caoc.accountservice.infrastructure.drivenadapters.mongo.documents;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "catalogs")
public class CatalogDto {
    private String id;
    private String value;
    private String name;
}
