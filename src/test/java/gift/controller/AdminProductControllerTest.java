package gift.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(AdminProductController.class)
class AdminProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    // GET /admin/products 테스트
    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 성공: 상품이 존재하면 목록 페이지를 올바르게 렌더링한다")
    void getAllProducts_shouldRenderWithProducts() throws Exception {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "상품1", 1000, "http://test.com/img1.jpg"),
                new Product(2L, "상품2", 2000, "http://test.com/img2.jpg")
        );
        ResponseEntity<List<Product>> successResponse = new ResponseEntity<>(mockProducts,
                HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(successResponse);

        mockMvc.perform(get("/admin/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_list"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", mockProducts))
                .andExpect(content().string(containsString("<td>1</td>")))
                .andExpect(content().string(containsString("<td>상품1</td>")))
                .andExpect(content().string(
                        containsString("<a href=\"/admin/products/1/edit\">수정</a>")))
                .andExpect(content().string(allOf(
                        containsString("<button"),
                        containsString("data-id=\"1\""),
                        containsString("onclick=\"deleteProduct(this.dataset.id)\""),
                        containsString(">삭제</button>")
                )));
    }

    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 성공: 상품이 없으면 200 OK와 빈 배열을 반환한다")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNoProducts() throws Exception {
        ResponseEntity<List<Product>> emptyResponse = new ResponseEntity<>(Collections.emptyList(),
                HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(emptyResponse);

        mockMvc.perform(get("/admin/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_list"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", Collections.emptyList()));
    }

    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 실패: API 호출 중 ResourceAccessException 발생 시 목록으로 리다이렉트하며 에러 메시지 전달")
    void getAllProducts_shouldHandleResourceAccessException() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new ResourceAccessException("Connection refused"));

        mockMvc.perform(get("/admin/products"))
                .andExpect(status().is3xxRedirection()) // 리다이렉션으로 변경
                .andExpect(redirectedUrlPattern(
                        "/admin/products?error=API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.")); // 정확한 메시지 패턴
    }

    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 실패: API 호출 중 기타 예외 발생 시 목록으로 리다이렉트하며 에러 메시지 전달")
    void getAllProducts_shouldHandleGenericException() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new RuntimeException("예상치 못한 오류 발생"));

        mockMvc.perform(get("/admin/products"))
                .andExpect(status().is3xxRedirection()) // 리다이렉션으로 변경
                .andExpect(redirectedUrlPattern(
                        "/admin/products?error=상품 데이터를 가져오는 중 알 수 없는 오류가 발생했습니다.")); // 정확한 메시지 패턴
    }

    // POST /admin/products 테스트 (상품 추가)
    @Test
    @DisplayName("POST /admin/products - 상품 추가 성공: 유효한 요청이면 /admin/products로 리다이렉트한다")
    void addProduct_shouldRedirectOnSuccess() throws Exception {
        Product newProduct = new Product(3L, "새로운 상품", 1000, "http://new.img/new.jpg");
        ResponseEntity<Product> successResponse = new ResponseEntity<>(newProduct,
                HttpStatus.CREATED);

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenReturn(successResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3")
                        .param("name", "새로운 상품")
                        .param("price", "1000")
                        .param("imageUrl", "http://new.img/new.jpg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/products"));
    }

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
                        .param("name", "")
                        .param("price", "1000")
                        .param("imageUrl", "http://valid.img/valid.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage",
                        "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요."));
    }

    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API에서 409 Conflict 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void addProduct_shouldReturnFormAndErrorMessageOnConflict() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT, "ID already exists"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "1")
                        .param("name", "충돌 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://conflict.img/conflict.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "이미 존재하는 ID입니다. 다른 ID를 사용해 주세요."));
    }

    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API 호출 중 ResourceAccessException 발생 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void addProduct_shouldHandleResourceAccessException() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new ResourceAccessException("Connection refused"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3")
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://error.img/error.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(
                        model().attribute("errorMessage", "API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요."));
    }

    @Test
    @DisplayName("POST /admin/products - 상품 추가 실패: API 호출 중 기타 예외 발생 시 폼 페이지로 돌아가며 일반 에러 메시지 표시")
    void addProduct_shouldHandleGenericError() throws Exception {
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new RuntimeException("알 수 없는 오류"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .param("id", "3")
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://error.img/error.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(
                        model().attribute("errorMessage", "상품 추가 중 예상치 못한 오류가 발생했습니다: 알 수 없는 오류"));
    }

    // GET /admin/products/new 테스트 (기존)
    @Test
    @DisplayName("GET /admin/products/new - 상품 추가 폼 페이지를 올바르게 렌더링한다")
    void showAddProductForm_shouldRenderForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", org.hamcrest.Matchers.notNullValue()))
                .andExpect(model().attribute("formAction", "/admin/products"))
                .andExpect(model().attribute("pageTitle", "새 상품 추가"));
    }

    // GET /admin/products/{id}/edit 테스트
    @Test
    @DisplayName("GET /admin/products/{id}/edit - 상품 수정 폼 페이지를 올바르게 렌더링한다 (상품 존재 시)")
    void showEditProductForm_shouldRenderFormWithExistingProduct() throws Exception {
        long existingProductId = 1L;
        Product existingProduct = new Product(existingProductId, "수정될 상품", 5000,
                "http://edit.img/original.jpg");
        ResponseEntity<Product> successResponse = new ResponseEntity<>(existingProduct,
                HttpStatus.OK);

        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.GET),
                any(),
                eq(Product.class)
        )).thenReturn(successResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/{id}/edit", existingProductId))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", existingProduct))
                .andExpect(model().attribute("formAction", "/admin/products/" + existingProductId))
                .andExpect(model().attribute("pageTitle", "상품 수정"))
                .andExpect(model().attribute("_method", "PUT"));
    }

    @Test
    @DisplayName("GET /admin/products/{id}/edit - 상품이 없으면 /admin/products로 리다이렉트하며 에러 메시지 전달")
    void showEditProductForm_shouldRedirectWhenProductNotFound() throws Exception {
        long nonExistingProductId = 999L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + nonExistingProductId),
                eq(HttpMethod.GET),
                any(),
                eq(Product.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "수정할 상품을 찾을 수 없습니다. " + nonExistingProductId));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin/products/{id}/edit", nonExistingProductId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/products?error=수정할 상품을 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("GET /admin/products/{id}/edit - API 호출 중 ResourceAccessException 발생 시 목록으로 리다이렉트하며 에러 메시지 전달")
    void showEditProductForm_shouldHandleResourceAccessException() throws Exception {
        long productId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + productId),
                eq(HttpMethod.GET),
                any(),
                eq(Product.class)
        )).thenThrow(new ResourceAccessException("Connection refused"));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/{id}/edit", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(
                        "/admin/products?error=상품 정보를 가져오는 API 서버에 연결할 수 없습니다.")); // 정확한 메시지
    }

    @Test
    @DisplayName("GET /admin/products/{id}/edit - API 호출 중 기타 예외 발생 시 목록으로 리다이렉트하며 에러 메시지 전달")
    void showEditProductForm_shouldHandleGenericError() throws Exception {
        long productId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + productId),
                eq(HttpMethod.GET),
                any(),
                eq(Product.class)
        )).thenThrow(new RuntimeException("알 수 없는 오류"));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/{id}/edit", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(
                        "/admin/products?error=상품 수정 폼 로드 중 알 수 없는 오류가 발생했습니다.*")); // 정확한 메시지
    }

    // PUT /admin/products/{id} 테스트
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

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "수정된 상품")
                        .param("price", "6000")
                        .param("imageUrl", "http://updated.img/updated.jpg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/products"));
    }

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
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage",
                        "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요."));
    }

    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API에서 404 Not Found 반환 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void updateProduct_shouldReturnErrorPageOnNotFound() throws Exception {
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
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "수정하려는 상품을 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API 호출 중 ResourceAccessException 발생 시 폼 페이지로 돌아가며 에러 메시지 표시")
    void updateProduct_shouldHandleResourceAccessException() throws Exception {
        long existingProductId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new ResourceAccessException("Connection refused"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://updated.img/error.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(
                        model().attribute("errorMessage", "API 서버에 연결할 수 없거나 상품 수정 중 오류가 발생했습니다."));
    }

    @Test
    @DisplayName("PUT /admin/products/{id} - 상품 수정 실패: API 호출 중 기타 예외 발생 시 폼 페이지로 돌아가며 일반 에러 메시지 표시")
    void updateProduct_shouldHandleGenericError() throws Exception {
        long existingProductId = 1L;
        when(restTemplate.exchange(
                eq("http://localhost:8080/api/products/" + existingProductId),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Product.class)
        )).thenThrow(new RuntimeException("알 수 없는 오류"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/{id}", existingProductId)
                        .param("_method", "PUT")
                        .param("id", String.valueOf(existingProductId))
                        .param("name", "오류 상품")
                        .param("price", "100")
                        .param("imageUrl", "http://updated.img/error.jpg"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product_form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(
                        model().attribute("errorMessage", "상품 수정 중 예상치 못한 오류가 발생했습니다: 알 수 없는 오류"));
    }
}
