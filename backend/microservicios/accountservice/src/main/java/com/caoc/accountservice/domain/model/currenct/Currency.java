package com.caoc.accountservice.domain.model.currenct;

import com.caoc.accountservice.domain.model.catalog.Catalog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {
    private String id;
    private String name;
}
