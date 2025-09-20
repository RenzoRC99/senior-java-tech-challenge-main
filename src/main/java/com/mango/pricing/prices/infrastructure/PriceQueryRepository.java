package com.mango.pricing.prices.infrastructure;

import com.mango.catalog.products.domain.ProductId;
import com.mango.pricing.prices.domain.Price;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PriceQueryRepository {
    Optional<Price> findCurrentPrice(ProductId productId, LocalDate date);
    List<Price> findPriceHistory(ProductId productId);
}
