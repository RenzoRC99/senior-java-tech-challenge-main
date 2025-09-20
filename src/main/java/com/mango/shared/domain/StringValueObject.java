package com.mango.shared.domain;

public abstract class StringValueObject extends BaseValueObject<String> {
    protected StringValueObject(String value) {
        super(value);
    }
}