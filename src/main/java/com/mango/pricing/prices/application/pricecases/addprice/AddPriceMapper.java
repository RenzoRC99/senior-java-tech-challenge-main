package com.mango.pricing.prices.application.pricecases.addprice;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddPriceMapper {

    public AddPriceCommand toCommand(UUID productId, UUID priceId, AddPriceRequest request) {
        return new AddPriceCommand(
                priceId,
                productId,
                request.value(),
                request.initDate(),
                request.endDate()
        );
    }
}
