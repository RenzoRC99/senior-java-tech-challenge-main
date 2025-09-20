package com.mango.pricing.prices.infrastructure.mapper;

import com.mango.pricing.prices.domain.Price;
import com.mango.pricing.prices.infrastructure.entity.PriceEntity;
import com.mango.shared.infrastructure.mapper.InfrastructureMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper implements InfrastructureMapper<Price, PriceEntity> {

    @Override
    public Price toDomain(PriceEntity entity) {
        return Price.create(
                entity.getId(),
                entity.getProductId(),
                entity.getValue(),
                entity.getInitDate(),
                entity.getEndDate()
        );
    }

    @Override
    public PriceEntity toEntity(Price domain) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(domain.getId().value());
        priceEntity.setProductId(domain.getProductId().value());
        priceEntity.setValue(domain.getValue().value());
        priceEntity.setInitDate(domain.getInitDate().value());
        priceEntity.setEndDate(domain.getEndDate().value());

        return priceEntity;
    }
}
