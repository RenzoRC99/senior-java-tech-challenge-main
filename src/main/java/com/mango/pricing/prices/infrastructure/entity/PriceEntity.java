package com.mango.pricing.prices.infrastructure.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prices")
public class PriceEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "productId", nullable = false)
    private UUID productId;

    @Column(name = "priceValue", nullable = false)
    private BigDecimal value;

    @Column(name = "initDate", nullable = false)
    private LocalDate initDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    // Constructor vacío para JPA
    public PriceEntity() {}

    public void setId(UUID id) { this.id = id; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public void setValue(BigDecimal value) { this.value = value; }
    public void setInitDate(LocalDate initDate) { this.initDate = initDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public UUID getId() { return id; }
    public UUID getProductId() { return productId; }
    public BigDecimal getValue() { return value; }
    public LocalDate getInitDate() { return initDate; }
    public LocalDate getEndDate() { return endDate; }
}
