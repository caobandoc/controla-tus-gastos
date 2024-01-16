package com.caoc.accountservice.domain.model.typeaccount;

import com.caoc.accountservice.domain.model.catalog.Catalog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeAccount{
    private String id;
    private String name;
}
