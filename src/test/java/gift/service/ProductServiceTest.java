package gift.service;

import gift.common.exception.ProductNotFoundException;
import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.UpdateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    Product product;

    @BeforeEach
    void before() {
        CreateProductRequest createProductRequest = new CreateProductRequest("칫솔", 10000, 12);
        product = productService.saveProduct(createProductRequest);
    }

    @Test
    @DisplayName("사용자는 상품을 저장할 수 있다.")
    void test1() {
        CreateProductRequest createProductRequest = new CreateProductRequest("칫솔", 10000, 12);

        Product product = productService.saveProduct(createProductRequest);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("칫솔");
        assertThat(product.getPrice()).isEqualTo(10000);
        assertThat(product.getQuantity()).isEqualTo(12);

    }

    @Test
    @DisplayName("사용자는 상품을 수정할 수 있다.")
    void test2() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("칫솔2", 30000, 111);
        Product update = productService.updateProduct(product.getId(), updateProductRequest);

        assertThat(update.getId()).isEqualTo(product.getId());
        assertThat(update.getName()).isEqualTo("칫솔2");
        assertThat(update.getPrice()).isEqualTo(30000);
        assertThat(update.getQuantity()).isEqualTo(111);
    }

    @Test
    @DisplayName("사용자는 상품 목록을 조회할 수 있다.")
    void test3() {
        CreateProductRequest createProductRequest = new CreateProductRequest("칫솔", 10000, 12);
        productService.saveProduct(createProductRequest);

        //beforeEach에서 생성한 것 까지 총 2건의 데이터 있음
        List<Product> products = productService.getAllProducts();
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자는 상품 단건을 조회할 수 있다.")
    void test4() {
        Product getProduct = productService.getProduct(this.product.getId());

        assertThat(getProduct.getId()).isEqualTo(product.getId());
        assertThat(getProduct.getName()).isEqualTo("칫솔");
        assertThat(getProduct.getPrice()).isEqualTo(10000);
        assertThat(getProduct.getQuantity()).isEqualTo(12);
    }

    @Test
    @DisplayName("사용자는 상품을 삭제할 수 있다.")
    void test5() {
        productService.deleteProduct(product.getId());

        assertThatThrownBy(() -> productService.getProduct(product.getId())).isInstanceOf(ProductNotFoundException.class);
    }
}