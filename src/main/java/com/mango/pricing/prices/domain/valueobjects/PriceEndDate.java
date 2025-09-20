package com.mango.pricing.prices.domain.valueobjects;

import com.mango.shared.domain.DateTimeValueObject;

import java.time.LocalDate;

public final class PriceEndDate extends DateTimeValueObject {

    public PriceEndDate(LocalDate date) {
        super(date,true);
    }
}
