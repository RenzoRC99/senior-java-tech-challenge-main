package com.mango.pricing.prices.application.pricecases.getcurrentprice;

import com.mango.catalog.products.domain.ProductId;
import com.mango.pricing.prices.infrastructure.PriceQueryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCurrentPriceHandler {
    private final PriceQueryRepository priceQueryRepository;

    public GetCurrentPriceHandler(PriceQueryRepository priceQueryRepository) {
        this.priceQueryRepository = priceQueryRepository;
    }

    public Optional<CurrentPriceDTO> handle(GetCurrentPriceQuery query) {
        // El repositorio ya devuelve solo el precio vigente para la fecha indicada
        return priceQueryRepository.findCurrentPrice(
                new ProductId(query.productId()), query.date()
        ).map(price -> new CurrentPriceDTO(
                price.getValue().value()
        ));
    }
}
