package gift.domain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.domain.product.dto.ProductRequest;
import jakarta.annotation.Nullable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        productRequest = new ProductRequest("감자", 1000, "https://test.com/img.jpg");
    }

    @Test
    @DisplayName("ADD")
    void addProduct() throws Exception {
        String content = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET")
    void getProduct() throws Exception {
        String content = objectMapper.writeValueAsString(productRequest);

        String location = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andReturn()
            .getResponse()
            .getHeader("Location");

        mockMvc.perform(get(Objects.requireNonNull(location)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("감자"));
    }

    @Test
    @DisplayName("UPDATE")
    void updateProduct() throws Exception {
        String content = objectMapper.writeValueAsString(productRequest);

        String location = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andReturn()
            .getResponse()
            .getHeader("Location");

        ProductRequest updateRequest = new ProductRequest("고구마", 2000, "https://test.com/img2.jpg");

        mockMvc.perform(put(Objects.requireNonNull(location))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE")
    void deleteProduct() throws Exception {
        String content = objectMapper.writeValueAsString(productRequest);

        String location = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andReturn()
            .getResponse()
            .getHeader("Location");

        mockMvc.perform(delete(location))
            .andExpect(status().isNoContent());
    }
}