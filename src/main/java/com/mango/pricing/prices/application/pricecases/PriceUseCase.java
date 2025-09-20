package com.mango.pricing.prices.application.pricecases;

import com.mango.pricing.prices.domain.PriceRepository;

public class PriceUseCase {
    protected PriceRepository priceRepository;

    public PriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
}
