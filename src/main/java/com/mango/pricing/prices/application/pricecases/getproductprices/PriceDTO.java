package com.mango.pricing.prices.application.pricecases.getproductprices;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PriceDTO(
        BigDecimal value,
        LocalDate initDate,
        LocalDate endDate
) {}
