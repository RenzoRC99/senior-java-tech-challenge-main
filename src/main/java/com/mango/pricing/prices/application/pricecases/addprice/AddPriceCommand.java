package com.mango.pricing.prices.application.pricecases.addprice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AddPriceCommand(
        UUID priceId,      // UUID del Price enviado por el cliente
        UUID productId,    // UUID del producto
        BigDecimal value,
        LocalDate initDate,
        LocalDate endDate
) {}
