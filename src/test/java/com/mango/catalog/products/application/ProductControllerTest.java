package com.mango.catalog.products.application;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    private ProductRepository productRepository;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productController = new ProductController(productRepository);
    }

    @Test
    @DisplayName("Crear producto válido devuelve 201 y llama al repositorio")
    void testCreateProductValid() {
        // Arrange
        String id = UUID.randomUUID().toString();
        CreateProductRequest request = new CreateProductRequest(id, "Camiseta Azul", "Algodón, talla M");

        // Act
        ResponseEntity<String> response = productController.createProduct(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(id));

        // Verificar que el repositorio recibió el producto
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(captor.capture());
        Product savedProduct = captor.getValue();
        assertEquals(id, savedProduct.getId().toString());
        assertEquals("Camiseta Azul", savedProduct.getName());
        assertEquals("Algodón, talla M", savedProduct.getDescription());
    }

    @Test
    @DisplayName("Crear producto con UUID inválido lanza IllegalArgumentException")
    void testCreateProductInvalidUUID() {
        // Arrange
        CreateProductRequest request = new CreateProductRequest("no-un-uuid", "Nombre", "Desc");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productController.createProduct(request));

        // Verificar que el repositorio nunca fue llamado
        verify(productRepository, never()).save(any());
    }

}