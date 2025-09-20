package com.mango.pricing.prices.domain.valueobjects;

import com.mango.shared.domain.BigDecimalValueObject;

import java.math.BigDecimal;

public final class PriceValue extends BigDecimalValueObject {

    public PriceValue(BigDecimal value) {
        super(value);
    }
}
