package com.mango.pricing.prices.application.pricecases.addprice;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AddPriceRequest(
        BigDecimal value,
        LocalDate initDate,
        LocalDate endDate // puede ser null
) {}