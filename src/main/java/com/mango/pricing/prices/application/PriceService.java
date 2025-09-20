package com.mango.pricing.prices.application;

import com.mango.pricing.prices.domain.Price;
import com.mango.pricing.prices.domain.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Crear un precio
    public void createPrice(Price price) {
        priceRepository.save(price);
    }

    // Actualizar un precio
    public void updatePrice(Price price) {
        // Podrías agregar validaciones: verificar que existe antes de actualizar
        Optional<Price> existing = priceRepository.findById(price.getId().value());
        if (existing.isEmpty()) {
            throw new NullPointerException("Price not found with id: " + price.getId());
        }
        priceRepository.save(price);
    }

    // Borrar un precio por id
    public void deletePrice(UUID id) {
        priceRepository.deleteById(id);
    }

    // Listar todos los precios
    public List<Price> listPrices() {
        return priceRepository.findAll();
    }

    // Buscar un precio por id
    public Price findPriceById(UUID id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price not found with id: " + id));
    }
}
