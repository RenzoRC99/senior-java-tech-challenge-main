package com.mango.catalog.products.infrastructure.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {
    @Test
    @DisplayName("Debe poder crearse usando el constructor vacío")
    void defaultConstructor_shouldCreateInstance() {
        ProductEntity entity = new ProductEntity();
        assertNotNull(entity);
    }

    @Test
    @DisplayName("Debe almacenar y devolver los valores establecidos con los getters y setters")
    void settersAndGetters_shouldStoreAndReturnValues() {
        UUID id = UUID.randomUUID();
        String name = "Test product";
        String description = "Test description";

        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setDescription(description);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(description, entity.getDescription());
    }

    @Test
    @DisplayName("Debe permitir valores nulos en los campos (name y description)")
    void shouldAllowNullValues() {
        ProductEntity entity = new ProductEntity();
        entity.setId(null);
        entity.setName(null);
        entity.setDescription(null);

        assertNull(entity.getId());
        assertNull(entity.getName());
        assertNull(entity.getDescription());
    }

    @Test
    @DisplayName("Debe permitir actualizar los valores después de haberlos establecido")
    void shouldAllowUpdatingValues() {
        ProductEntity entity = new ProductEntity();

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        entity.setId(id1);
        entity.setName("Old name");
        entity.setDescription("Old description");

        entity.setId(id2);
        entity.setName("New name");
        entity.setDescription("New description");

        assertEquals(id2, entity.getId());
        assertEquals("New name", entity.getName());
        assertEquals("New description", entity.getDescription());
    }
}
