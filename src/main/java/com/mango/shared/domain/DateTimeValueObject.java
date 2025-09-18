package com.mango.shared.domain;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class DateTimeValueObject {
    private final Instant date;

    protected DateTimeValueObject(Instant date) {
        this.date = date;
    }

    public Instant value() {
        return this.date;
    }

    public String valueString() {
        // Formatear como ISO_ZONED_DATE_TIME con zona UTC para mantener formato legible
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneOffset.UTC).format(this.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTimeValueObject that = (DateTimeValueObject) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return valueString();
    }
}
