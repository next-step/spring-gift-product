package gift.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import gift.model.Product;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(AdminProductController.class)
class AdminProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    // GET
    @Test

    @DisplayName("GET /admin/products - 상품 목록 조회 성공: 상품이 존재하면 200 OK와 상품 목록을 반환한다")
    void getAllProducts_shouldReturnOkWithProducts() throws Exception {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "상품1", 1000, "image1.jpg"),
                new Product(2L, "상품2", 2000, "image2.jpg")
        );
        ResponseEntity<List<Product>> successResponse = new ResponseEntity<>(mockProducts,
                HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(successResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", mockProducts));
    }

    // GET
    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 성공: 상품이 없으면 200 OK와 빈 배열을 반환한다")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNoProducts() throws Exception {
        ResponseEntity<List<Product>> emptyResponse = new ResponseEntity<>(
                Collections.emptyList(), HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(emptyResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("products", Collections.emptyList()));
    }

    // GET
    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 실패: RestTemplate 호출 중 오류 발생 시 200 OK와 빈 배열을 반환한다 (내부 처리)")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenRestTemplateError() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new RuntimeException("API 호출 실패 시뮬레이션"));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("products", Collections.emptyList()));
    }

    // GET
    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 성공: RestTemplate 응답 바디가 null일 때 200 OK와 빈 배열을 반환한다")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNullBody() throws Exception {
        ResponseEntity<List<Product>> nullBodyResponse = new ResponseEntity<>(null,
                HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(nullBodyResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("products", Collections.emptyList()));
    }

    // POST
    @Test
    @DisplayName("POST /admin/products - 상품 추가 성공: 유효한 요청이면 /admin/products로 리다이렉트")
    void addProduct_shouldRedirectOnSuccess() throws Exception {
        Product newProduct = new Product(3L, "새로운 상품", 1000, "http://new.img/new.jpg");
        ResponseEntity<Product> successResponse = new ResponseEntity<>(newProduct,
                HttpStatus.CREATED);

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class), // 어떤 Product 객체라도 매칭
                eq(Product.class)
        )).thenReturn(successResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3") // 폼 필드 파라미터
                        .param("name", "새로운 상품")
                        .param("price", "1000")
                        .param("imageUrl", "http://new.img/new.jpg"))
                .andExpect(MockMvcResultMatchers.status()
                        .is3xxRedirection()) // 302 Found 또는 303 See Other
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    // POST
    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API에서 400 Bad Request 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void addProduct_shouldReturnFormAndErrorMessageOnBadRequest() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid input"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3")
                        .param("name", "") // 유효하지 않은 이름
                        .param("price", "1000")
                        .param("imageUrl", "http://valid.img/valid.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 리다이렉트가 아닌 200 OK로 폼 페이지 반환
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model().attribute("product",
                        org.hamcrest.Matchers.notNullValue())); // Product 객체가 다시 바인딩되었는지 확인
    }

    // POST
    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API에서 409 Conflict 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void addProduct_shouldReturnFormAndErrorMessageOnConflict() throws Exception {
        // ProductController에서 409 Conflict를 반환하는 HttpClientErrorException 모의
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT, "ID already exists"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "1") // 기존 ID
                        .param("name", "충돌 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://conflict.img/conflict.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"));
    }

    // POST
    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API 호출 중 일반적인 예외 발생 시 폼 페이지로 돌아가며 일반 에러 메시지 표시")
    void addProduct_shouldReturnFormAndErrorMessageOnGenericError() throws Exception {
        // RestTemplate 호출 중 일반 런타임 예외 발생 모의
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new RuntimeException("네트워크 오류 또는 기타 서버 오류"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3")
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://error.img/error.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"));
    }

    // GET /admin/products/new 테스트
    @Test
    @DisplayName("GET /admin/products/new - 상품 추가 폼 페이지를 올바르게 렌더링한다")
    void showAddProductForm_shouldRenderForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
                .andExpect(MockMvcResultMatchers.model().attribute("product",
                        org.hamcrest.Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("formAction", "/admin/products"))
                .andExpect(MockMvcResultMatchers.model().attribute("pageTitle", "새 상품 추가"));
    }

    // PUT
    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 성공: 유효한 요청이면 /admin/products로 리다이렉트한다")
    void updateProduct_shouldRedirectOnSuccess() throws Exception {
        long existingProductId = 1L;
        Product updatedProduct = new Product(existingProductId, "수정된 상품", 6000,
                "http://updated.img/updated.jpg");
        ResponseEntity<Product> successResponse = new ResponseEntity<>(updatedProduct,
                HttpStatus.OK);

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenReturn(successResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}",
                                existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "수정된 상품")
                        .param("price", "6000")
                        .param("imageUrl", "http://updated.img/updated.jpg"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    // PUT
    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API에서 400 Bad Request 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void updateProduct_shouldReturnFormAndErrorMessageOnBadRequest() throws Exception {
        long existingProductId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid update data"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "")
                        .param("price", "6000")
                        .param("imageUrl", "http://updated.img/invalid.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("product", org.hamcrest.Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("pageTitle", "상품 수정"));
    }

    // PUT
    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API에서 404 Not Found 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void updateProduct_shouldReturnFormAndErrorMessageOnNotFound() throws Exception {
        long nonExistingProductId = 999L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + nonExistingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", nonExistingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(nonExistingProductId))
                        .param("name", "없는 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://updated.img/notfound.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model().attribute("pageTitle", "상품 수정"));
    }

    // PUT
    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API 호출 중 일반적인 예외 발생 시 폼 페이지로 돌아가며 일반 에러 메시지 표시")
    void updateProduct_shouldReturnFormAndErrorMessageOnGenericError() throws Exception {
        long existingProductId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new RuntimeException("네트워크 오류"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://updated.img/error.jpg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/product_form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"))
                .andExpect(MockMvcResultMatchers.model().attribute("pageTitle", "상품 수정"));
    }
}
