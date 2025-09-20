package com.mango.pricing.prices.application.pricecases.addprice;

import com.mango.catalog.products.domain.ProductId;
import com.mango.catalog.products.domain.ProductRepository;
import com.mango.pricing.prices.application.pricecases.PriceUseCase;
import com.mango.pricing.prices.domain.Price;
import com.mango.pricing.prices.domain.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AddPriceHandler extends PriceUseCase {
    private final ProductRepository productRepository;

    public AddPriceHandler(PriceRepository priceRepository, ProductRepository productRepository) {
        super(priceRepository);
        this.productRepository = productRepository;
    }

    @Transactional
    public void handle(AddPriceCommand cmd) {
        ProductId productId = new ProductId(cmd.productId());

        // Validación de existencia
        if (productRepository.findById(productId).isEmpty()) {
            throw new NullPointerException("Product not found");
        }

        // Obtener precios existentes del producto
        List<Price> existingPrices = priceRepository.findByProductId(productId);

        // Validación de solapamiento
        validateNoOverlap(cmd.initDate(), cmd.endDate(), existingPrices);

        // Crear Price de dominio
        Price price = Price.create(
                cmd.priceId(),
                cmd.productId(),
                cmd.value(),
                cmd.initDate(),
                cmd.endDate()
        );

        // Guardar en repositorio
        priceRepository.save(price);

    }

    private void validateNoOverlap(LocalDate newInit, LocalDate newEnd, List<Price> existingPrices) {
        LocalDate newEndSafe = newEnd != null ? newEnd : LocalDate.MAX;

        for (Price p : existingPrices) {
            LocalDate existingEnd = p.getEndDate() != null ? p.getEndDate().value() : LocalDate.MAX;

            if (!newInit.isAfter(existingEnd) && !p.getInitDate().value().isAfter(newEndSafe)) {
                throw new IllegalArgumentException(
                        "New price overlaps with existing price from " +
                                p.getInitDate() + " to " + p.getEndDate()
                );
            }
        }
    }

}
