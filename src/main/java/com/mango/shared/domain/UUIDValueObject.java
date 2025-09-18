package com.mango.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class UUIDValueObject {

    private final UUID uuid;

    protected UUIDValueObject(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID no puede ser null");
        }
        this.uuid = uuid;
    }

    public static UUID fromStringOrGenerate(String value) {
        return (value == null || value.isEmpty())
                ? UUID.randomUUID()
                : UUID.fromString(value);
    }

    public UUID value() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UUIDValueObject that = (UUIDValueObject) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
