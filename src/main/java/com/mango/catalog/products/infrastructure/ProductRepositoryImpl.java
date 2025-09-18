package com.mango.catalog.products.infrastructure;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.domain.ProductId;
import com.mango.catalog.products.domain.ProductRepository;
import com.mango.catalog.products.infrastructure.entity.ProductEntity;
import com.mango.catalog.products.infrastructure.mapper.ProductMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;
    private final ProductMapper mapper;

    public ProductRepositoryImpl(EntityManager entityManager, ProductMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        ProductEntity entity = entityManager.find(ProductEntity.class, id.value());
        return Optional.ofNullable(entity).map(mapper::toDomain);
    }

    @Override
    public void delete(ProductId id) {
        ProductEntity entity = entityManager.find(ProductEntity.class, id.value());
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        entityManager.merge(entity);
    }
}
