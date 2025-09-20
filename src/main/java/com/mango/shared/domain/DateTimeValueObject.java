package com.mango.shared.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateTimeValueObject extends BaseValueObject<LocalDate> {
    protected DateTimeValueObject(LocalDate date) {
        super(date);
    }
    protected DateTimeValueObject(LocalDate date, boolean allowNull) {
        super(date, allowNull);
    }

    @Override
    protected String formatValue() {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(value());
    }
}
