package com.mango.pricing.prices.domain.valueobjects;

import com.mango.shared.domain.UUIDValueObject;

import java.util.UUID;

public final class PriceId extends UUIDValueObject {

    public PriceId(UUID value) {
        super(value);
    }
}
