package com.mango.pricing.prices.infrastructure;

import com.mango.catalog.products.domain.ProductId;
import com.mango.pricing.prices.domain.Price;
import com.mango.pricing.prices.infrastructure.entity.PriceEntity;
import com.mango.pricing.prices.infrastructure.mapper.PriceMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceQueryRepositoryImpl implements PriceQueryRepository {

    private final EntityManager em;
    private final PriceMapper priceMapper;


    public PriceQueryRepositoryImpl(EntityManager em, PriceMapper priceMapper) {
        this.em = em;
        this.priceMapper = priceMapper;
    }

    @Override
    public Optional<Price> findCurrentPrice(ProductId productId, LocalDate date) {
        return em.createQuery(
                        "SELECT p FROM PriceEntity p WHERE p.productId = :pid " +
                                "AND p.initDate <= :date " +
                                "AND (p.endDate IS NULL OR p.endDate >= :date)", PriceEntity.class)
                .setParameter("pid", productId.value())
                .setParameter("date", date)
                .getResultStream()
                .findFirst()
                .map(priceMapper::toDomain);
    }

    @Override
    public List<Price> findPriceHistory(ProductId productId) {
        List<PriceEntity> entities = em.createQuery(
                        "SELECT p FROM PriceEntity p WHERE p.productId = :pid ORDER BY p.initDate", PriceEntity.class)
                .setParameter("pid", productId.value())
                .getResultList();

        return entities.stream()
                .map(priceMapper::toDomain)
                .toList();
    }
}
