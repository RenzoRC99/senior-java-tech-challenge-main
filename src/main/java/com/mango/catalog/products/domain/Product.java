package com.mango.catalog.products.domain;

import java.util.UUID;

public class Product {
    private final ProductId id;
    private ProductName name;
    private ProductDescription description;

    protected Product(ProductId id, ProductName name, ProductDescription description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Product create(
            UUID id,
            String name,
            String description
    ) {
        return new Product(
            new ProductId(id),
            new ProductName(name),
            new ProductDescription(description)
        );
    }

    public UUID getId() {
        return id.value();
    }

    public String getName() {
        return name.value();
    }

    public String getDescription() {
        return description.value();
    }

    public void rename(ProductName newName) {
        this.name = newName;
    }

    public void changeDescription(ProductDescription newDescription) {
        this.description = newDescription;
    }
}
