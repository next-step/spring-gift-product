package gift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ProductCollectorTest {

    @Test
    void crudTest() {
        ProductCollector productCollector = new ProductCollector();

        // add
        Product product = new Product("아메리카노", 3000L);
        Product result = productCollector.add(product);
        assertProduct(result, 0L, "아메리카노", 3000L);

        product = new Product("카페라떼", 4500L);
        result = productCollector.add(product);
        assertProduct(result, 1L, "카페라떼", 4500L);

        // get
        result = productCollector.get(0L);
        assertProduct(result, 0L, "아메리카노", 3000L);

        result = productCollector.get(1L);
        assertProduct(result, 1L, "카페라떼", 4500L);

        // getAll
        List<Product> listResult = productCollector.getAll();
        assertProduct(listResult.get(0), 0L, "아메리카노", 3000L);
        assertProduct(listResult.get(1), 1L, "카페라떼", 4500L);

        // update
        product = new Product("아이스 아메리카노", 3500L);
        productCollector.update(0L, product);
        result = productCollector.get(0L);
        assertProduct(result, 0L, "아이스 아메리카노", 3500L);

        // delete
        productCollector.delete(0L);
        result = productCollector.get(0L);
        assertNull(result);
    }

    void assertProduct(Product result, long expectedId, String expectedName, long expectedPrice) {
        assertEquals(expectedId, result.getId());
        assertEquals(expectedName, result.getName());
        assertEquals(expectedPrice, result.getPrice());
    }
}
