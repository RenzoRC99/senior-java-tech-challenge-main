package com.mango.catalog.products.application;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    @DisplayName("✅ Debería crear un producto y devolver su UUID")
    void shouldCreateProductAndReturnUuid() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        UUID productId = UUID.randomUUID();
        String name = "Camisa";
        String description = "Camisa de algodón";

        // Act
        UUID returnedId = productService.create(productId.toString(), name, description);

        // Assert
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        assertEquals(productId, savedProduct.getId(), "El UUID del producto guardado debe coincidir");
        assertEquals(name, savedProduct.getName());
        assertEquals(description, savedProduct.getDescription());

        assertEquals(productId, returnedId, "El UUID devuelto debe coincidir con el del producto");
    }

    @Test
    @DisplayName("❌ Debería lanzar IllegalArgumentException si el UUID es inválido")
    void shouldThrowExceptionForInvalidUuid() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        String invalidUuid = "no-es-un-uuid";

        // Act & Assert
        try {
            productService.create(invalidUuid, "Camisa", "Camisa de algodón");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid UUID string: " + invalidUuid, e.getMessage().split(":")[0] + ": " + e.getMessage().split(":")[1].trim());
        }

        // Asegurarse de que no se llamó a save
        verify(productRepository, never()).save(any());
    }
}
