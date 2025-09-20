package com.mango.pricing.prices.domain;

import com.mango.catalog.products.domain.ProductId;
import com.mango.pricing.prices.domain.valueobjects.PriceEndDate;
import com.mango.pricing.prices.domain.valueobjects.PriceId;
import com.mango.pricing.prices.domain.valueobjects.PriceInitDate;
import com.mango.pricing.prices.domain.valueobjects.PriceValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public class Price {

    private final PriceId id;          // siempre inmutable
    private final ProductId productId;   // siempre inmutable
    private PriceValue value;
    private PriceInitDate initDate;
    private PriceEndDate endDate;

    public Price(PriceId id, ProductId productId, PriceValue value, PriceInitDate initDate, PriceEndDate endDate) {
        this.id = id;
        this.productId = productId;
        this.value = value;
        this.initDate = initDate;
        this.endDate = endDate;
    }

    public static Price create(
            UUID id,
            UUID productId,
            BigDecimal value,
            LocalDate initDate,
            LocalDate endDate
    ) {
        // Validación opcional: endDate >= initDate
        validateDates(initDate, endDate);

        return new Price(
                new PriceId(id),
                new ProductId(productId),
                new PriceValue(value),
                new PriceInitDate(initDate),
                new PriceEndDate(endDate)
        );
    }

    public PriceId getId() {
        return id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public PriceValue getValue() {
        return value;
    }

    public PriceInitDate getInitDate() {
        return initDate;
    }

    public PriceEndDate getEndDate() {
        return endDate;
    }

    public void updateValue(PriceValue newValue) {
        if (newValue == null) throw new IllegalArgumentException("El valor no puede ser null");
        this.value = newValue;
    }

    public void updateDates(PriceInitDate newInitDate, PriceEndDate newEndDate) {
        validateDates(newInitDate.value(), newEndDate.value());
        this.initDate = newInitDate;
        this.endDate = newEndDate;
    }

    private static void validateDates(LocalDate initDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(initDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

}
