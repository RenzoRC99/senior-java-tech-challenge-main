package com.mango.catalog.products.infrastructure;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductId;
import com.mango.catalog.products.infrastructure.entity.ProductEntity;
import com.mango.catalog.products.infrastructure.mapper.ProductMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryImplTest {
    private final EntityManager entityManager = mock(EntityManager.class);
    private final ProductMapper mapper = mock(ProductMapper.class);
    private final ProductRepositoryImpl repository = new ProductRepositoryImpl(entityManager, mapper);

    @Test
    @DisplayName("findById debe devolver el dominio cuando existe la entidad")
    void findById_shouldReturnDomainIfEntityExists() {
        UUID id = UUID.randomUUID();
        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setName("Camisa");
        entity.setDescription("Blanca");

        Product domain = Product.create(id, "Camisa", "Blanca");

        when(entityManager.find(ProductEntity.class, id)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        Optional<Product> result = repository.findById(new ProductId(id));

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(entityManager).find(ProductEntity.class, id);
        verify(mapper).toDomain(entity);
    }

    @Test
    @DisplayName("save debe convertir a entidad y hacer merge")
    void save_shouldConvertAndMergeEntity() {
        Product domain = Product.create(UUID.randomUUID(), "Camisa", "Blanca");
        ProductEntity entity = new ProductEntity();
        when(mapper.toEntity(domain)).thenReturn(entity);

        repository.save(domain);

        verify(mapper).toEntity(domain);
        verify(entityManager).merge(entity);
    }

    @Test
    @DisplayName("delete debe encontrar y eliminar la entidad si existe")
    void delete_shouldRemoveIfExists() {
        UUID id = UUID.randomUUID();
        ProductId productId = new ProductId(id);
        ProductEntity entity = new ProductEntity();
        when(entityManager.find(ProductEntity.class, id)).thenReturn(entity);

        repository.delete(productId);

        verify(entityManager).find(ProductEntity.class, id);
        verify(entityManager).remove(entity);
    }

    @Test
    @DisplayName("delete no debe llamar a remove si no existe")
    void delete_shouldDoNothingIfEntityNotFound() {
        UUID id = UUID.randomUUID();
        ProductId productId = new ProductId(id);
        when(entityManager.find(ProductEntity.class, id)).thenReturn(null);

        repository.delete(productId);

        verify(entityManager, never()).remove(any());
    }
}