package gift.domain;

import gift.dto.product.CreateProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductDomainTest {

    @Test
    @DisplayName("product 생성 시 product 내부의 static 변수 count를 id로 할당하고 count 증가되는지 확인")
    void test1() {
        Product p1 = Product.of(new CreateProductRequest("p1", 1000, 1));

        assertThat(p1.getId()).isEqualTo(1L);

        Product p2 = Product.of(new CreateProductRequest("p2", 2000, 2));

        assertThat(p2.getId()).isEqualTo(2L);

    }
}
