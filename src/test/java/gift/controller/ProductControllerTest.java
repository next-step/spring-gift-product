package gift.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gift.model.Product;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    private Map<Long, Product> productsField;

    // init
    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field field = ProductController.class.getDeclaredField("products");
        field.setAccessible(true);
        productsField = (Map<Long, Product>) field.get(productController);
        productsField.clear();

        productsField.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        productsField.put(1234567L, new Product(1234567L, "제주 한라봉 스무디", 6000,
            "https://tester.com/hallabong.jpg"));
    }

    // GET /api/products
    @Test
    @DisplayName("모든 상품 조회: 상품이 존재하면 200 OK와 상품 목록을 반환한다")
    void getAllProducts_shouldReturnOkWithProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[?(@.id == 8146027 && @.name == '아이스 카페 아메리카노 T')]").exists())
            .andExpect(jsonPath("$[?(@.id == 1234567 && @.name == '제주 한라봉 스무디')]").exists());
    }

    // GET /api/products
    @Test
    @DisplayName("모든 상품 조회: 상품이 없으면 200 OK와 빈 배열을 반환한다")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNoProducts() throws Exception {
        productsField.clear();

        mockMvc.perform(get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    // GET /api/products/{id}
    @Test
    @DisplayName("특정 상품 조회: 상품이 존재하면 200 OK와 해당 상품을 반환한다")
    void getProductById_shouldReturnOkWithProductWhenExists() throws Exception {
        long existingProductId = 8146027L;

        mockMvc.perform(get("/api/products/{id}", existingProductId)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is((int) existingProductId)))
            .andExpect(jsonPath("$.name", is("아이스 카페 아메리카노 T")));
    }

    // GET /api/products/{id}
    @Test
    @DisplayName("특정 상품 조회: 상품이 없으면 404 Not Found를 반환한다")
    void getProductById_shouldReturnNotFoundWhenNotExists() throws Exception {
        long nonExistingProductId = 99999999L;

        mockMvc.perform(get("/api/products/{id}", nonExistingProductId)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string(""));
    }

    // POST /api/products
    @Test
    @DisplayName("상품 추가: 유효한 요청이면 201 Created와 생성된 상품을 반환한다")
    void createProduct_shouldReturnCreatedWithProductWhenValid() throws Exception {
        String newProductJson = "{\"id\": 3000000, \"name\": \"새로운 과자\", \"price\": 2500, \"imageUrl\": \"http://example.com/new_snack.jpg\"}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(3000000)))
            .andExpect(jsonPath("$.name", is("새로운 과자")))
            .andExpect(jsonPath("$.price", is(2500)));
    }

    // POST /api/products
    @Test
    @DisplayName("상품 추가: 이미 존재하는 ID로 요청하면 409 Conflict를 반환한다")
    void createProduct_shouldReturnConflictWhenIdAlreadyExists() throws Exception {
        long existingId = 8146027L;
        String conflictProductJson = "{\"id\": " + existingId
            + ", \"name\": \"충돌 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/conflict.jpg\"}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conflictProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
    }

    // POST /api/products
    @Test
    @DisplayName("상품 추가: 이름이 없으면 400 Bad Request를 반환한다")
    void createProduct_shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        String invalidProductJson = "{\"id\": 3000001, \"name\": \"\", \"price\": 2500, \"imageUrl\": \"http://example.com/invalid_name.jpg\"}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // POST /api/products
    @Test
    @DisplayName("상품 추가: 가격이 0 이하면 400 Bad Request를 반환한다")
    void createProduct_shouldReturnBadRequestWhenPriceIsInvalid() throws Exception {
        String invalidProductJson = "{\"id\": 3000002, \"name\": \"테스트상품\", \"price\": 0, \"imageUrl\": \"http://example.com/test_price.jpg\"}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // POST /api/products
    @Test
    @DisplayName("상품 추가: 이미지 URL이 유효하지 않으면 400 Bad Request를 반환한다")
    void createProduct_shouldReturnBadRequestWhenImageUrlIsInvalid() throws Exception {
        String invalidProductJson = "{\"id\": 3000003, \"name\": \"테스트상품\", \"price\": 1000, \"imageUrl\": \"invalid-url\"}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // PUT /api/products/{id}
    @Test
    @DisplayName("상품 수정: 상품이 존재하고 유효하며 ID가 일치하면 200 OK와 수정된 상품을 반환한다")
    void updateProduct_shouldReturnOkWithUpdatedProductWhenExistsAndValidAndIdMatches()
        throws Exception {
        long existingProductId = 8146027L;
        String updatedProductJson = "{\"id\": 8146027, \"name\": \"업데이트된 아메리카노\", \"price\": 5000, \"imageUrl\": \"http://example.com/updated_americano.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", existingProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is((int) existingProductId)))
            .andExpect(jsonPath("$.name", is("업데이트된 아메리카노")))
            .andExpect(jsonPath("$.price", is(5000)));
    }

    // PUT /api/products/{id}
    @Test
    @DisplayName("상품 수정: 상품이 없으면 404 Not Found를 반환한다")
    void updateProduct_shouldReturnNotFoundWhenNotExists() throws Exception {
        long nonExistingProductId = 99999999L;
        String updatedProductJson = "{\"id\": 99999999, \"name\": \"없는 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/no_product.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", nonExistingProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    // PUT /api/products/{id}
    @Test
    @DisplayName("상품 수정: URL 경로 ID와 요청 본문 ID가 일치하지 않으면 400 Bad Request를 반환한다")
    void updateProduct_shouldReturnBadRequestWhenIdMismatch() throws Exception {
        long urlId = 8146027L;
        long bodyId = 1234567L;
        String updatedProductJson = "{\"id\": " + bodyId
            + ", \"name\": \"ID 불일치 상품\", \"price\": 100, \"imageUrl\": \"http://example.com/mismatch.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", urlId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // PUT /api/products/{id}
    @Test
    @DisplayName("상품 수정: 이름이 없으면 400 Bad Request를 반환한다")
    void updateProduct_shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        long existingProductId = 8146027L;
        String invalidProductJson = "{\"id\": 8146027, \"name\": \"\", \"price\": 5000, \"imageUrl\": \"http://example.com/updated.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", existingProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // PUT /api/products/{id}
    @Test
    @DisplayName("상품 수정: 가격이 0 이하면 400 Bad Request를 반환한다")
    void updateProduct_shouldReturnBadRequestWhenPriceIsInvalid() throws Exception {
        long existingProductId = 8146027L;
        String invalidProductJson = "{\"id\": 8146027, \"name\": \"테스트상품\", \"price\": 0, \"imageUrl\": \"http://example.com/test_price.jpg\"}";

        mockMvc.perform(put("/api/products/{id}", existingProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    // DELETE /api/products/{id}
    @Test
    @DisplayName("상품 삭제: 상품이 존재하면 204 No Content를 반환하고 상품이 삭제된다")
    void deleteProduct_shouldReturnNoContentWhenExists() throws Exception {
        long existingProductId = 8146027L;

        mockMvc.perform(delete("/api/products/{id}", existingProductId)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andExpect(content().string(""));

        mockMvc.perform(get("/api/products/{id}", existingProductId))
            .andExpect(status().isNotFound());
    }

    // DELETE /api/products/{id}
    @Test
    @DisplayName("상품 삭제: 상품이 없으면 404 Not Found를 반환한다")
    void deleteProduct_shouldReturnNotFoundWhenNotExists() throws Exception {
        long nonExistingProductId = 99999999L;

        mockMvc.perform(delete("/api/products/{id}", nonExistingProductId)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}