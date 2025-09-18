package com.mango.catalog.products.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {
    @Test
    @DisplayName("Debe crear un producto correctamente con sus valores iniciales")
    void createProduct() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        String name = "Camisa CV B";
        String description = "Camiseta de corte en V de color blanca";

        // Act
        Product product = Product.create(uuid, name, description);

        // Assert
        assertNotNull(product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
    }

    @Test
    @DisplayName("Debe cambiar el nombre del producto al llamar a rename()")
    void rename_shouldChangeProductName() {
        // given
        Product product = Product.create(
                UUID.randomUUID(),
                "Old Name",
                "Some description"
        );

        ProductName newName = new ProductName("New Name");

        // when
        product.rename(newName);

        // then
        assertEquals("New Name", product.getName());
    }

    @Test
    @DisplayName("Debe cambiar la descripción del producto al llamar a changeDescription()")
    void changeDescription_shouldChangeProductDescription() {
        // given
        Product product = Product.create(
                UUID.randomUUID(),
                "Some name",
                "Old description"
        );

        ProductDescription newDescription = new ProductDescription("New description");

        // when
        product.changeDescription(newDescription);

        // then
        assertEquals("New description", product.getDescription());
    }
}