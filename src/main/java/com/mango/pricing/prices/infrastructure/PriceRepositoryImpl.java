package com.mango.pricing.prices.infrastructure;

import com.mango.catalog.products.domain.ProductId;
import com.mango.pricing.prices.domain.Price;
import com.mango.pricing.prices.domain.PriceRepository;
import com.mango.pricing.prices.infrastructure.entity.PriceEntity;
import com.mango.pricing.prices.infrastructure.mapper.PriceMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final EntityManager entityManager;
    private final PriceMapper priceMapper;

    public PriceRepositoryImpl(EntityManager entityManager, PriceMapper priceMapper) {
        this.entityManager = entityManager;
        this.priceMapper = priceMapper;
    }

    @Override
    public void save(Price price) {
        entityManager.persist(priceMapper.toEntity(price));
    }

    @Override
    public void update(Price price) {
        entityManager.merge(priceMapper.toEntity(price));
    }

    @Override
    public Optional<Price> findById(UUID id) {
        PriceEntity entity = entityManager.find(PriceEntity.class, id);
        if (entity == null) return Optional.empty();
        return Optional.of(priceMapper.toDomain(entity));
    }

    @Override
    public void deleteById(UUID id) {
        PriceEntity entity = entityManager.find(PriceEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<Price> findAll() {
        List<PriceEntity> entities = entityManager
                .createQuery("SELECT p FROM PriceEntity p", PriceEntity.class)
                .getResultList();
        return entities.stream()
                .map(priceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Price> findByProductId(ProductId productId) {
        return entityManager.createQuery(
                        "SELECT p FROM PriceEntity p WHERE p.productId = :productId", PriceEntity.class)
                .setParameter("productId", productId.value())
                .getResultList()
                .stream()
                .map(priceMapper::toDomain)
                .toList();
    }


}
