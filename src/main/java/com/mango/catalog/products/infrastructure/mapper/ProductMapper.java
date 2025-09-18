package com.mango.catalog.products.infrastructure.mapper;

import com.mango.catalog.products.domain.Product;
import com.mango.catalog.products.infrastructure.entity.ProductEntity;
import com.mango.shared.infrastructure.mapper.InfrastructureMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements InfrastructureMapper<Product, ProductEntity>  {
    @Override
    public Product toDomain(ProductEntity entity) {
        return Product.create(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    @Override
    public ProductEntity toEntity(Product domain) {
        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());

        return  entity;
    }
}
