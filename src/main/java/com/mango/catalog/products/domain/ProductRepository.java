package com.mango.catalog.products.domain;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId id);

    void save(Product product);

    void delete(ProductId id);
}
