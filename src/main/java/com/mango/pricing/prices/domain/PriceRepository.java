package com.mango.pricing.prices.domain;

import com.mango.catalog.products.domain.ProductId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceRepository {

    void save(Price price);

    void update(Price price);
    
    Optional<Price> findById(UUID id);

    void deleteById(UUID id);

    List<Price> findAll();

    List<Price> findByProductId(ProductId productId);
}
