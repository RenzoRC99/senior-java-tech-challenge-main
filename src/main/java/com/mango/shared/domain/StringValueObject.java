package com.mango.shared.domain;

import java.util.Objects;

public abstract class StringValueObject {

    private final String value;

    protected StringValueObject(String stringValue) {
        this.value = stringValue;
    }

    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValueObject that = (StringValueObject) o;
        return value.equals(that.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}