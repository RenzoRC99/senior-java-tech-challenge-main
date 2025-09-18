package com.mango.catalog.products.application;

import com.mango.catalog.products.domain.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Product product = Product.create(
                UUID.fromString(request.id()),
                request.name(),
                request.description()
        );
        productRepository.save(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Producto creado con ID: " + product.getId());
    }
}
