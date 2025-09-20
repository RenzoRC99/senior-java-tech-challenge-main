package com.mango.pricing.prices.application.pricecases.getproductprices;

import java.util.List;

public record ProductPricesDTO(
        String name,
        String description,
        List<PriceDTO> prices
) {}