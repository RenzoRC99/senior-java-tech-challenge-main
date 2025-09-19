package com.mango.catalog.products.application;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public UUID create(String id, String name, String description) {
        Product product = Product.create(
                UUID.fromString(id),
                name,
                description
        );
        productRepository.save(product);
        return product.getId();
    }
}
