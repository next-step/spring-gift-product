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

class InMemoryProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryProductRepository();
        repository.deleteAll();
    }

    @Test
    void 상품을_저장하고_ID가_할당되는지_확인() {
        // Given
        Product product = Product.of("Test Product", 1000, "test_image.jpg");

        // When
        repository.save(product);

        // Then
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
        // Given
        Product product1 = Product.of("Product A", 100, "a.jpg");
        Product product2 = Product.of("Product B", 200, "b.jpg");

        // When
        repository.save(product1);
        repository.save(product2);

        // Then
        List<Product> allProducts = repository.findAll();
        assertEquals(2, allProducts.size());
        assertNotEquals(allProducts.get(0).id(), allProducts.get(1).id());
    }

    @Test
    void 존재하는_ID로_상품을_조회할_때_상품이_반환되는지_확인() {
        // Given
        Product product = Product.of("Find Me", 500, "findme.jpg");
        repository.save(product);
        Long savedId = repository.findAll().get(0).id();

        // When
        Optional<Product> foundProduct = repository.findById(savedId);

        // Then
        assertTrue(foundProduct.isPresent());
        assertEquals(savedId, foundProduct.get().id());
        assertEquals("Find Me", foundProduct.get().name());
    }

    @Test
    void 존재하지_않는_ID로_상품을_조회할_때_Optional_empty가_반환되는지_확인() {
        // Given

        // When
        Optional<Product> foundProduct = repository.findById(999L);

        // Then
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void 저장된_모든_상품_리스트를_반환하는지_확인() {
        // Given
        repository.save(Product.of("Prod1", 10, "p1.jpg"));
        repository.save(Product.of("Prod2", 20, "p2.jpg"));

        // When
        List<Product> products = repository.findAll();

        // Then
        assertEquals(2, products.size());
    }

    @Test
    void 상품이_없을_때_빈_리스트를_반환하는지_확인() {
        // Given

        // When
        List<Product> products = repository.findAll();

        // Then
        assertTrue(products.isEmpty());
    }

    @Test
    void 존재하는_상품의_정보를_성공적으로_업데이트하는지_확인() {
        // Given
        Product originalProduct = Product.of("Old Name", 100, "old.jpg");
        repository.save(originalProduct);
        Long idToUpdate = repository.findAll().get(0).id();
        Product updatedDetails = Product.of("New Name", 200, "new.jpg");

        // When
        repository.update(idToUpdate, updatedDetails);

        // Then
        Optional<Product> foundProduct = repository.findById(idToUpdate);
        assertTrue(foundProduct.isPresent());
        assertEquals("New Name", foundProduct.get().name());
        assertEquals(200, foundProduct.get().price());
        assertEquals("new.jpg", foundProduct.get().imageUrl());
    }

    @Test
    void 존재하지_않는_ID로_업데이트_시_IllegalArgumentException_발생하는지_확인() {
        // Given
        Product updatedDetails = Product.of("Non Existent", 500, "none.jpg");
        Long nonExistentId = 999L;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> repository.update(nonExistentId, updatedDetails));
        assertEquals("존재하지 않는 상품 ID입니다: " + nonExistentId, exception.getMessage());
    }

    @Test
    void 존재하는_ID로_상품을_삭제하는지_확인() {
        // Given
        Product product = Product.of("To Delete", 100, "delete.jpg");
        repository.save(product);
        Long idToDelete = repository.findAll().get(0).id();

        // When
        repository.deleteById(idToDelete);

        // Then
        Optional<Product> foundProduct = repository.findById(idToDelete);
        assertFalse(foundProduct.isPresent());
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void 존재하지_않는_ID로_삭제_시_아무런_변화_없는_지_확인() {
        // Given
        repository.save(Product.of("Existing", 100, "exist.jpg"));
        int initialSize = repository.findAll().size();
        Long nonExistentId = 999L;

        // When
        repository.deleteById(nonExistentId);

        // Then
        assertEquals(initialSize, repository.findAll().size());
    }

    @Test
    void 모든_상품을_삭제하는지_확인() {
        // Given
        repository.save(Product.of("Product1", 10, "p1.jpg"));
        repository.save(Product.of("Product2", 20, "p2.jpg"));
        assertFalse(repository.findAll().isEmpty());

        // When
        repository.deleteAll();

        // Then
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void 여러_스레드에서_동시에_상품_저장_시_데이터_일관성_및_중복_ID_없는지_확인() throws InterruptedException {
        // Given
        int numberOfThreads = 100;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // When
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

        // Then
        assertEquals(numberOfThreads, repository.findAll().size());
        long distinctIds = repository.findAll().stream().map(Product::id).distinct().count();
        assertEquals(numberOfThreads, distinctIds);
    }
}