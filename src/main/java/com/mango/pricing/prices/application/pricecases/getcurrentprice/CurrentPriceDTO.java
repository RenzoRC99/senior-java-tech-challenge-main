package com.mango.pricing.prices.application.pricecases.getcurrentprice;

import java.math.BigDecimal;

public record CurrentPriceDTO(
        BigDecimal value
) {}