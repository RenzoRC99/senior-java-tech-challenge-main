package com.mango.shared.infrastructure.mapper;

public interface InfrastructureMapper<D,E> {
    D toDomain(E entity);
    E toEntity(D domain);
}
