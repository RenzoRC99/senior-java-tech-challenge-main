package com.mango.shared.domain;

import java.util.Objects;

public abstract class BaseValueObject<T> {

    private final T value;

    // Constructor nuevo: permite controlar si el valor puede ser null
    protected BaseValueObject(T value, boolean allowNull) {
        if (!allowNull && value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        this.value = value;
    }

    // Constructor original para compatibilidad con clases existentes
    protected BaseValueObject(T value) {
        this(value, false); // llama al constructor principal con allowNull=false
    }

    public T value() {
        return value;
    }

    // Permite que subclases sobreescriban el formato del toString
    protected String formatValue() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseValueObject<?> that = (BaseValueObject<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return formatValue();
    }
}
