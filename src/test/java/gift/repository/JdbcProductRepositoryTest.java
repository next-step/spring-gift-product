package gift.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gift.domain.Product;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(JdbcProductRepository.class)
class JdbcProductRepositoryTest {

    @Autowired
    private JdbcProductRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void 상품을_저장하고_ID가_할당되는지_확인() {
        Product product = Product.of("Test Product", 1000, "test_image.jpg");
        repository.save(product);

        List<Product> allProducts = repository.findAll();
        assertFalse(allProducts.isEmpty());
        Product savedProduct = allProducts.get(0);

        assertNotNull(savedProduct.id());
        assertEquals("Test Product", savedProduct.name());
        assertEquals(1000, savedProduct.price());
        assertEquals("test_image.jpg", savedProduct.imageUrl());
    }

    @Test
    void 여러_상품을_저장했을_때_각기_다른_ID가_할당되는지_확인() {
        Product product1 = Product.of("Product A", 100, "a.jpg");
        Product product2 = Product.of("Product B", 200, "b.jpg");

        repository.save(product1);
        repository.save(product2);

        List<Product> allProducts = repository.findAll();
        assertEquals(2, allProducts.size());
        assertNotEquals(allProducts.get(0).id(), allProducts.get(1).id());
    }

    @Test
    void 존재하는_ID로_상품을_조회할_때_상품이_반환되는지_확인() {
        Product product = Product.of("Find Me", 500, "findme.jpg");
        repository.save(product);
        Long savedId = repository.findAll().get(0).id();

        Optional<Product> foundProduct = repository.findById(savedId);

        assertTrue(foundProduct.isPresent());
        assertEquals(savedId, foundProduct.get().id());
        assertEquals("Find Me", foundProduct.get().name());
    }

    @Test
    void 존재하지_않는_ID로_상품을_조회할_때_Optional_empty가_반환되는지_확인() {
        Optional<Product> foundProduct = repository.findById(999L);
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void 존재하는_상품의_정보를_성공적으로_업데이트하는지_확인() {
        Product originalProduct = Product.of("Old Name", 100, "old.jpg");
        repository.save(originalProduct);
        Long idToUpdate = repository.findAll().get(0).id();
        Product updatedDetails = Product.of("New Name", 200, "new.jpg");

        repository.update(idToUpdate, updatedDetails);

        Optional<Product> foundProduct = repository.findById(idToUpdate);
        assertTrue(foundProduct.isPresent());
        assertEquals("New Name", foundProduct.get().name());
        assertEquals(200, foundProduct.get().price());
        assertEquals("new.jpg", foundProduct.get().imageUrl());
    }

    @Test
    void 존재하지_않는_ID로_업데이트_시_IllegalArgumentException_발생하는지_확인() {
        Product updatedDetails = Product.of("Non Existent", 500, "none.jpg");
        Long nonExistentId = 999L;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> repository.update(nonExistentId, updatedDetails));
        assertEquals("해당 ID에 대한 상품이 존재하지 않아 업데이트할 수 없습니다: " + nonExistentId,
                exception.getMessage());
    }

    @Test
    void 존재하는_ID로_상품을_삭제하는지_확인() {
        Product product = Product.of("To Delete", 100, "delete.jpg");
        repository.save(product);
        Long idToDelete = repository.findAll().get(0).id();

        repository.deleteById(idToDelete);

        Optional<Product> foundProduct = repository.findById(idToDelete);
        assertFalse(foundProduct.isPresent());
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void 모든_상품을_삭제하는지_확인() {
        repository.save(Product.of("Product1", 10, "p1.jpg"));
        repository.save(Product.of("Product2", 20, "p2.jpg"));
        assertFalse(repository.findAll().isEmpty());

        repository.deleteAll();
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void 여러_스레드에서_동시에_상품_저장_시_데이터_일관성_및_중복_ID_없는지_확인() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadNum = i;
            service.submit(() -> {
                try {
                    Product product = Product.of("Concurrent Product " + threadNum, 100 + threadNum,
                            "conc" + threadNum + ".jpg");
                    repository.save(product);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        service.shutdown();

        assertEquals(numberOfThreads, repository.findAll().size());
        long distinctIds = repository.findAll().stream().map(Product::id).distinct().count();
        assertEquals(numberOfThreads, distinctIds);
    }
}
