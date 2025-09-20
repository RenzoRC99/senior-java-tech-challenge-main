package com.mango.shared.domain;

import java.util.UUID;

public abstract class UUIDValueObject extends BaseValueObject<UUID> {
    protected UUIDValueObject(UUID value) {
        super(value);
    }
}

