package com.mango.catalog.products.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("✅ Debería crear un producto correctamente y devolver 201 con el UUID del dominio")
    void shouldCreateProductSuccessfully() throws Exception {
        UUID productId = UUID.randomUUID();
        String productJson = """
            {
              "id": "%s",
              "name": "Zapatos",
              "description": "Zapatos deportivos"
            }
            """.formatted(productId);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Producto creado con ID: " + productId)));
    }

    @Test
    @DisplayName("❌ Debería fallar con 400 si falta el nombre del producto")
    void shouldFailWhenNameIsMissing() throws Exception {
        UUID productId = UUID.randomUUID();
        String productJson = """
            {
              "id": "%s",
              "description": "Zapatos deportivos"
            }
            """.formatted(productId);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("❌ Debería fallar con 400 si el UUID no es válido")
    void shouldFailWhenUuidIsInvalid() throws Exception {
        String productJson = """
            {
              "id": "not-a-uuid",
              "name": "Zapatos",
              "description": "Zapatos deportivos"
            }
            """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
    }
}
