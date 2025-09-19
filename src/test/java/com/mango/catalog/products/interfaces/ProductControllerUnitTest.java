package com.mango.catalog.products.interfaces;

import com.mango.catalog.products.application.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("✅ Debería crear un producto correctamente y devolver 201 con el UUID")
    void shouldCreateProductSuccessfully() throws Exception {
        UUID productId = UUID.randomUUID();

        // Simulamos el comportamiento del service
        doReturn(productId).when(productService)
                .create(anyString(), Mockito.eq("Camisa"), Mockito.eq("Camisa de algodón"));

        String requestJson = """
                {
                  "id": "%s",
                  "name": "Camisa",
                  "description": "Camisa de algodón"
                }
                """.formatted(productId);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("Producto creado con ID: " + productId));
    }

    @Test
    @DisplayName("❌ Debería devolver 400 si el request es inválido")
    void shouldReturnBadRequestOnInvalidInput() throws Exception {
        String invalidJson = """
                {
                  "id": "not-a-uuid",
                  "name": "",
                  "description": "Camisa de algodón"
                }
                """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
