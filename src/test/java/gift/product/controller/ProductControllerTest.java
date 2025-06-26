package gift.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.product.domain.Product;
import gift.product.dto.ProductRequestDto;
import gift.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("ProductController 테스트")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        //productService.deleteAllProducts();
        ProductRequestDto requestDto1 = new ProductRequestDto("Test1", 1000, "Test1.jpg");
        ProductRequestDto requestDto2 = new ProductRequestDto("Test2", 1200, "Test2.jpg");

        product1 = productService.saveProduct(requestDto1);
        product2 = productService.saveProduct(requestDto2);
    }

    @Test
    @DisplayName("상품 등록 테스트 - 201")
    void addProduct() throws Exception {
        // given
        ProductRequestDto newProduct = new ProductRequestDto("new", 1500, "new.png");
        String content = objectMapper.writeValueAsString(newProduct);

        // when
        // then
        mockMvc.perform(post("/api/products")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("전체 상품 조회 테스트 - 200")
    void getProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].name", is("Test2")))
                .andDo(print());
    }
}
