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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
        ResponseEntity<List<Product>> successResponse = new ResponseEntity<>(mockProducts, HttpStatus.OK);
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
        ResponseEntity<List<Product>> emptyResponse = new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
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
                .andExpect(MockMvcResultMatchers.model().attribute("products", Collections.emptyList()));
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
                .andExpect(MockMvcResultMatchers.model().attribute("products", Collections.emptyList()));
    }

    // GET
    @Test
    @DisplayName("GET /admin/products - 상품 목록 조회 성공: RestTemplate 응답 바디가 null일 때 200 OK와 빈 배열을 반환한다")
    void getAllProducts_shouldReturnOkWithEmptyArrayWhenNullBody() throws Exception {
        ResponseEntity<List<Product>> nullBodyResponse = new ResponseEntity<>(null, HttpStatus.OK);
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
                .andExpect(MockMvcResultMatchers.model().attribute("products", Collections.emptyList()));
    }
}