package com.mango.catalog.products.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record CreateProductRequest(
        @UUID(message = "El id debe ser un UUID válido")
        String id,                     // UUID generado por el front

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String name,                   // Nombre del producto

        @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
        String description             // Descripción del producto
) {}
