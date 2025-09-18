package com.mango.catalog.products.domain;

import com.mango.shared.domain.UUIDValueObject;

import java.util.UUID;

public class ProductId extends UUIDValueObject {
    public ProductId(UUID id) {
        super(id);
    }
}
