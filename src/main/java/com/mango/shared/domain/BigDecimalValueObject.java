package com.mango.shared.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BigDecimalValueObject extends BaseValueObject<BigDecimal> {
    private static final BigDecimal MAX_PRICE = new BigDecimal("1000000");

    protected BigDecimalValueObject(BigDecimal value) {
        super(validate(value));
    }

    private static BigDecimal validate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (value.compareTo(MAX_PRICE) > 0) {
            throw new IllegalArgumentException("El precio excede el máximo permitido");
        }
        // Fijar 2 decimales
        return value.setScale(2, RoundingMode.HALF_UP);

    }
}
