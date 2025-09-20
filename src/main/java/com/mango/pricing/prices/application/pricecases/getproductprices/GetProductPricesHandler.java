package com.mango.pricing.prices.application.pricecases.getproductprices;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductId;
import com.mango.catalog.products.domain.ProductRepository;
import com.mango.pricing.prices.infrastructure.PriceQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductPricesHandler {
    private final PriceQueryRepository priceRepo;
    private final ProductRepository productRepo;

    public GetProductPricesHandler(PriceQueryRepository priceRepo, ProductRepository productRepo) {
        this.priceRepo = priceRepo;
        this.productRepo = productRepo;
    }

    public ProductPricesDTO handle(GetProductPricesQuery query) {
        ProductId productId = new ProductId(query.id());

        // Buscar producto y lanzar excepción si no existe
        Product product = productRepo.findById(productId)
                .orElseThrow(NullPointerException::new);

        // Obtener historial de precios
        List<PriceDTO> priceDTOs = priceRepo.findPriceHistory(productId).stream()
                .map(p -> new PriceDTO(
                        p.getValue().value(),
                        p.getInitDate().value(),
                        p.getEndDate() != null ? p.getEndDate().value() : null
                ))
                .toList();

        return new ProductPricesDTO(
                product.getName().value(),
                product.getDescription().value(),
                priceDTOs
        );
    }

}
