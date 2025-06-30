package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.domain.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("상품 등록 성공")
    void createProduct() throws Exception {
        Product input = new Product(null, "아메리카노", 4500, "http://image.url");
        Product saved = new Product(1L, "아메리카노", 4500, "http://image.url");

        when(productService.create(any(Product.class))).thenReturn(saved);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("아메리카노"));
    }

    @Test
    @DisplayName("전체 상품 조회")
    void getAllProducts() throws Exception {
        List<Product> products = List.of(
                new Product(1L, "커피", 4000, "http://image.url/1"),
                new Product(2L, "녹차", 4200, "http://image.url/2")
        );
        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("단일 상품 조회 성공")
    void getProductByIdSuccess() throws Exception {
        Product product = new Product(1L, "커피", 4000, "http://image.url");
        when(productService.getById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("커피"));
    }

    @Test
    @DisplayName("상품 수정 성공")
    void updateProductSuccess() throws Exception {
        Product updated = new Product(1L, "업데이트커피", 4700, "http://image.url");
        doNothing().when(productService).update(eq(1L), any(Product.class));

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isNoContent());  // 204 기대
    }


    @Test
    @DisplayName("상품 삭제 성공")
    void deleteProductSuccess() throws Exception {
        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }

}

