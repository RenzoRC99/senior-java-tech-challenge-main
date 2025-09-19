package com.mango.catalog.products.interfaces;

import com.mango.catalog.products.application.ProductRequest;
import com.mango.catalog.products.application.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest request) {

        UUID id = productService.create(request.id(), request.name(),  request.description());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Producto creado con ID: " + id.toString());
    }
}
