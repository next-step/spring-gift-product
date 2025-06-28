package gift.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gift.model.Product;
import gift.service.ProductService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        reset(productService);
    }

    @Test
    @DisplayName("GET /api/products - 모든 상품 조회 성공: 200 OK와 상품 목록 반환")
    void getAllProducts_shouldReturnOkWithProducts() throws Exception {
        List<Product> mockProducts = Arrays.asList(
                new Product(8146027L, "아이스 카페 아메리카노 T", 4500,
                        "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"),
                new Product(1234567L, "제주 한라봉 스무디", 6000, "https://tester.com/hallabong.jpg")
        );
        when(productService.getAllProducts()).thenReturn(mockProducts);

        mockMvc.perform(get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[?(@.id == 8146027 && @.name == '아이스 카페 아메리카노 T')]").exists())
                .andExpect(jsonPath("$[?(@.id == 1234567 && @.name == '제주 한라봉 스무디')]").exists());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    @DisplayName("GET /api/products - 모든 상품 조회 성공: 상품이 없으면 200 OK와 빈 배열 반환")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNoProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    @DisplayName("GET /api/products/{id} - 특정 상품 조회 성공: 200 OK와 해당 상품 반환")
    void getProductById_shouldReturnOkWithProductWhenExists() throws Exception {
        long existingProductId = 8146027L;
        Product mockProduct = new Product(existingProductId, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        when(productService.getProductById(eq(existingProductId))).thenReturn(
                Optional.of(mockProduct));

        mockMvc.perform(get("/api/products/{id}", existingProductId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is((int) existingProductId)))
                .andExpect(jsonPath("$.name", is("아이스 카페 아메리카노 T")));

        verify(productService, times(1)).getProductById(eq(existingProductId));
    }

    @Test
    @DisplayName("GET /api/products/{id} - 특정 상품 조회 실패: 상품 없으면 404 Not Found (ExceptionHandler 처리)")
    void getProductById_shouldReturnNotFoundWhenNotExists() throws Exception {
        long nonExistingProductId = 99999999L;
        when(productService.getProductById(eq(nonExistingProductId)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/{id}", nonExistingProductId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("상품을 찾을 수 없습니다: " + nonExistingProductId));

        verify(productService, times(1)).getProductById(eq(nonExistingProductId));
    }

    @Test
    @DisplayName("POST /api/products - 상품 추가 성공: 201 Created와 생성된 상품 반환")
    void createProduct_shouldReturnCreatedWithProductWhenValid() throws Exception {
        Product newProduct = new Product(3000000L, "새로운 과자", 2500,
                "http://example.com/new_snack.jpg");
        when(productService.createProduct(any(Product.class))).thenReturn(newProduct);

        String newProductJson = "{\"id\": 3000000, \"name\": \"새로운 과자\", \"price\": 2500, \"imageUrl\": \"http://example.com/new_snack.jpg\"}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3000000)))
                .andExpect(jsonPath("$.name", is("새로운 과자")));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    @DisplayName("POST /api/products - 상품 추가 실패: 이미 존재하는 ID로 요청하면 409 Conflict (ExceptionHandler 처리)")
    void createProduct_shouldReturnConflictWhenIdAlreadyExists() throws Exception {
        when(productService.createProduct(any(Product.class)))
                .thenThrow(new IllegalArgumentException("이미 존재하는 상품 ID입니다: 8146027"));

        long existingId = 8146027L;
        String conflictProductJson = "{\"id\": " + existingId
                + ", \"name\": \"충돌 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/conflict.jpg\"}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(conflictProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("이미 존재하는 상품 ID입니다: 8146027"));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    @DisplayName("POST /api/products - 상품 추가 실패: 유효성 검증 실패 시 400 Bad Request (ExceptionHandler 처리)")
    void createProduct_shouldReturnBadRequestWhenValidationFails() throws Exception {
        when(productService.createProduct(any(Product.class)))
                .thenThrow(new IllegalArgumentException("상품 데이터가 유효하지 않습니다"));

        String invalidProductJson = "{\"id\": 3000001, \"name\": \"\", \"price\": 2500, \"imageUrl\": \"http://example.com/invalid_name.jpg\"}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("상품 데이터가 유효하지 않습니다"));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} - 상품 수정 성공: 200 OK와 수정된 상품 반환")
    void updateProduct_shouldReturnOkWithUpdatedProductWhenValid() throws Exception {
        long existingProductId = 8146027L;
        Product updatedProduct = new Product(existingProductId, "업데이트된 아메리카노", 5000,
                "http://example.com/updated_americano.jpg");
        when(productService.updateProduct(eq(existingProductId), any(Product.class)))
                .thenReturn(updatedProduct);

        String updatedProductJson = "{\"id\": 8146027, \"name\": \"업데이트된 아메리카노\", \"price\": 5000, \"imageUrl\": \"http://example.com/updated_americano.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", existingProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is((int) existingProductId)))
                .andExpect(jsonPath("$.name", is("업데이트된 아메리카노")));

        verify(productService, times(1)).updateProduct(eq(existingProductId), any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} - 상품 수정 실패: 상품 없으면 404 Not Found (ExceptionHandler 처리)")
    void updateProduct_shouldReturnNotFoundWhenProductNotExists() throws Exception {
        long nonExistingProductId = 99999999L;
        when(productService.updateProduct(eq(nonExistingProductId), any(Product.class)))
                .thenThrow(new IllegalArgumentException("상품을 찾을 수 없습니다: " + nonExistingProductId));

        String updatedProductJson = "{\"id\": 99999999, \"name\": \"없는 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/no_product.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", nonExistingProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("상품을 찾을 수 없습니다: " + nonExistingProductId));

        verify(productService, times(1)).updateProduct(eq(nonExistingProductId),
                any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} - 상품 수정 실패: ID 불일치 시 400 Bad Request (ExceptionHandler 처리)")
    void updateProduct_shouldReturnBadRequestWhenIdMismatch() throws Exception {
        long urlId = 8146027L;
        long bodyId = 1234567L;
        when(productService.updateProduct(eq(urlId), any(Product.class)))
                .thenThrow(new IllegalArgumentException("URL ID와 요청 본문 ID가 일치하지 않습니다"));

        String updatedProductJson = "{\"id\": " + bodyId
                + ", \"name\": \"ID 불일치 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/mismatch.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", urlId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("URL ID와 요청 본문 ID가 일치하지 않습니다"));

        verify(productService, times(1)).updateProduct(eq(urlId), any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} - 상품 수정 실패: 유효성 검증 실패 시 400 Bad Request (ExceptionHandler 처리)")
    void updateProduct_shouldReturnBadRequestWhenValidationFails() throws Exception {
        long existingProductId = 8146027L;
        when(productService.updateProduct(eq(existingProductId), any(Product.class)))
                .thenThrow(new IllegalArgumentException("상품 데이터가 유효하지 않습니다"));

        String invalidProductJson = "{\"id\": 8146027, \"name\": \"\", \"price\": 5000, \"imageUrl\": \"http://example.com/updated.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", existingProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidProductJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("상품 데이터가 유효하지 않습니다"));

        verify(productService, times(1)).updateProduct(eq(existingProductId), any(Product.class));
    }

    @Test
    @DisplayName("DELETE /api/products/{id} - 상품 삭제 성공: 204 No Content 반환")
    void deleteProduct_shouldReturnNoContentWhenExists() throws Exception {
        long existingProductId = 8146027L;
        doNothing().when(productService).deleteProduct(eq(existingProductId));

        mockMvc.perform(delete("/api/products/{id}", existingProductId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(productService, times(1)).deleteProduct(eq(existingProductId));
    }

    @Test
    @DisplayName("DELETE /api/products/{id} - 상품 삭제 실패: 상품 없으면 404 Not Found (ExceptionHandler 처리)")
    void deleteProduct_shouldReturnNotFoundWhenNotExists() throws Exception {
        long nonExistingProductId = 99999999L;
        doThrow(new IllegalArgumentException("삭제할 상품을 찾을 수 없습니다: " + nonExistingProductId))
                .when(productService).deleteProduct(eq(nonExistingProductId));

        mockMvc.perform(delete("/api/products/{id}", nonExistingProductId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("삭제할 상품을 찾을 수 없습니다: " + nonExistingProductId));

        verify(productService, times(1)).deleteProduct(eq(nonExistingProductId));
    }
}
