package com.mango.pricing.prices.application.pricecases.getcurrentprice;

import java.time.LocalDate;
import java.util.UUID;

public record GetCurrentPriceQuery(
        UUID productId,
        LocalDate date
){}