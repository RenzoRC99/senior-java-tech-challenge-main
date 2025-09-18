package com.mango.catalog.products.infrastructure.mapper;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper mapper = new ProductMapper();

    @Test
    @DisplayName("Debe convertir ProductEntity a Product correctamente")
    void toDomain_shouldMapEntityToDomain() {
        UUID id = UUID.randomUUID();
        String name = "Camisa Azul";
        String description = "Camisa azul de algodón";

        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setDescription(description);

        Product product = mapper.toDomain(entity);

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
    }

    @Test
    @DisplayName("Debe convertir Product a ProductEntity correctamente")
    void toEntity_shouldMapDomainToEntity() {
        UUID id = UUID.randomUUID();
        String name = "Pantalón Negro";
        String description = "Pantalón de vestir negro";

        Product product = Product.create(id, name, description);

        ProductEntity entity = mapper.toEntity(product);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(description, entity.getDescription());
    }
}