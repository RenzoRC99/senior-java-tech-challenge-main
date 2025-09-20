package com.mango.pricing.prices.domain.valueobjects;

import com.mango.shared.domain.DateTimeValueObject;
import java.time.LocalDate;

public final class PriceInitDate extends DateTimeValueObject {

    public PriceInitDate(LocalDate date) {
        super(date);
    }
}
