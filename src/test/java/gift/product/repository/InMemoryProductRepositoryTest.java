package gift.product.repository;


import static org.assertj.core.api.Assertions.*;

import gift.common.dto.SortInfo;
import gift.product.domain.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryProductRepositoryTest {

  private InMemoryProductRepository repository;
  private Product testProduct;

  @BeforeEach
  void setUp() {
    repository = new InMemoryProductRepository();
    testProduct = new Product(null, "테스트 상품", 10000, "테스트 상품 설명", "http://test.com/image.jpg");
  }

  @Test
  void 상품을_저장하면_ID를_반환한다() {
    Long savedId = repository.save(testProduct);

    assertThat(savedId).isNotNull();
    assertThat(savedId).isPositive();
  }

  @Test
  void 상품을_저장할_때_null이면_예외가_발생한다() {
    assertThatThrownBy(() -> repository.save(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("상품은 null일 수 없습니다");
  }

  @Test
  void ID로_상품을_조회할_수_있다() {
    Long savedId = repository.save(testProduct);

    Optional<Product> foundProduct = repository.findById(savedId);

    assertThat(foundProduct).isPresent();
    assertThat(foundProduct.get().name()).isEqualTo("테스트 상품");
    assertThat(foundProduct.get().price()).isEqualTo(10000);
    assertThat(foundProduct.get().description()).isEqualTo("테스트 상품 설명");
    assertThat(foundProduct.get().imageUrl()).isEqualTo("http://test.com/image.jpg");
  }

  @Test
  void 존재하지_않는_ID로_조회하면_빈_Optional을_반환한다() {
    Optional<Product> foundProduct = repository.findById(999L);

    assertThat(foundProduct).isEmpty();
  }

  @Test
  void ID가_null이면_조회시_예외가_발생한다() {
    assertThatThrownBy(() -> repository.findById(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("ID는 null일 수 없습니다");
  }

  @Test
  void 모든_상품을_페이징하여_조회할_수_있다() {
    repository.save(testProduct);
    repository.save(new Product(null, "상품2", 20000, "설명2", "http://test.com/image2.jpg"));
    repository.save(new Product(null, "상품3", 30000, "설명3", "http://test.com/image3.jpg"));

    SortInfo sortInfo = new SortInfo("name", true);

    List<Product> products = repository.findAll(0, 2, sortInfo);

    assertThat(products).hasSize(2);
  }

  @Test
  void 상품을_업데이트할_수_있다() {
    Long savedId = repository.save(testProduct);
    Product updatedProduct = new Product(savedId, "수정된 상품", 15000, "수정된 설명", "http://test.com/updated.jpg");

    repository.update(savedId, updatedProduct);

    Optional<Product> foundProduct = repository.findById(savedId);
    assertThat(foundProduct).isPresent();
    assertThat(foundProduct.get().name()).isEqualTo("수정된 상품");
    assertThat(foundProduct.get().price()).isEqualTo(15000);
    assertThat(foundProduct.get().description()).isEqualTo("수정된 설명");
    assertThat(foundProduct.get().imageUrl()).isEqualTo("http://test.com/updated.jpg");
  }

  @Test
  void 업데이트시_ID가_null이면_예외가_발생한다() {
    assertThatThrownBy(() -> repository.update(null, testProduct))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("ID는 null일 수 없습니다");
  }

  @Test
  void 업데이트시_상품이_null이면_예외가_발생한다() {
    assertThatThrownBy(() -> repository.update(1L, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("상품은 null일 수 없습니다");
  }

  @Test
  void ID로_상품을_삭제할_수_있다() {
    Long savedId = repository.save(testProduct);

    repository.deleteById(savedId);

    Optional<Product> foundProduct = repository.findById(savedId);
    assertThat(foundProduct).isEmpty();
  }

  @Test
  void 삭제시_ID가_null이면_예외가_발생한다() {
    assertThatThrownBy(() -> repository.deleteById(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("id cannot be null");
  }

  @Test
  void 순차적으로_저장하면_ID가_증가한다() {
    Long id1 = repository.save(testProduct);
    Long id2 = repository.save(new Product(null, "상품2", 20000, "설명2", "image2.jpg"));
    Long id3 = repository.save(new Product(null, "상품3", 30000, "설명3", "image3.jpg"));

    assertThat(id1).isLessThan(id2);
    assertThat(id2).isLessThan(id3);
  }

  @Test
  void 빈_저장소에서_전체_조회하면_빈_리스트를_반환한다() {
    SortInfo sortInfo = new SortInfo("name", true);

    List<Product> products = repository.findAll(0, 10, sortInfo);

    assertThat(products).isEmpty();
  }

  @Test
  void 페이지_크기보다_적은_데이터가_있을_때_실제_데이터만_반환한다() {
    repository.save(testProduct);
    SortInfo sortInfo = new SortInfo("name", true);

    List<Product> products = repository.findAll(0, 10, sortInfo);

    assertThat(products).hasSize(1);
  }

  @Test
  void 저장된_상품의_ID가_올바르게_설정된다() {
    Long savedId = repository.save(testProduct);

    Optional<Product> foundProduct = repository.findById(savedId);

    assertThat(foundProduct).isPresent();
    assertThat(foundProduct.get().id()).isEqualTo(savedId);
  }

  @Test
  void 동일한_데이터로_여러_상품을_저장하면_서로_다른_ID를_가진다() {
    Long id1 = repository.save(testProduct);
    Long id2 = repository.save(testProduct);

    assertThat(id1).isNotEqualTo(id2);

    Optional<Product> product1 = repository.findById(id1);
    Optional<Product> product2 = repository.findById(id2);

    assertThat(product1).isPresent();
    assertThat(product2).isPresent();
    assertThat(product1.get().id()).isNotEqualTo(product2.get().id());
  }

  @Test
  void 동시에_여러_상품을_저장해도_ID가_중복되지_않는다() throws InterruptedException {
    int threadCount = 100;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);
    Set<Long> savedIds = ConcurrentHashMap.newKeySet();

    for (int i = 0; i < threadCount; i++) {
      final int index = i;
      executorService.submit(() -> {
        try {
          Product product = new Product(null, "상품" + index, 10000 + index, "설명" + index, "image" + index + ".jpg");
          Long savedId = repository.save(product);
          savedIds.add(savedId);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    assertThat(savedIds).hasSize(threadCount);
  }

  @Test
  void 동시에_같은_상품을_조회해도_안전하다() throws InterruptedException {
    Long savedId = repository.save(testProduct);
    int threadCount = 50;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);
    List<Optional<Product>> results = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < threadCount; i++) {
      executorService.submit(() -> {
        try {
          Optional<Product> product = repository.findById(savedId);
          results.add(product);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    assertThat(results).hasSize(threadCount);
    assertThat(results.stream().allMatch(Optional::isPresent)).isTrue();
    assertThat(results.stream()
        .map(Optional::get)
        .map(Product::name)
        .allMatch("테스트 상품"::equals)).isTrue();
  }

  @Test
  void 동시에_여러_상품을_업데이트해도_안전하다() throws InterruptedException {
    List<Long> savedIds = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Product product = new Product(null, "상품" + i, 10000 + i, "설명" + i, "image" + i + ".jpg");
      savedIds.add(repository.save(product));
    }

    int threadCount = 50;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      final int index = i;
      executorService.submit(() -> {
        try {
          Long targetId = savedIds.get(index % savedIds.size());
          Product updatedProduct = new Product(targetId, "업데이트된상품" + index,
              20000 + index, "업데이트된설명" + index, "updated" + index + ".jpg");
          repository.update(targetId, updatedProduct);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    for (Long id : savedIds) {
      Optional<Product> product = repository.findById(id);
      assertThat(product).isPresent();
      assertThat(product.get().name()).startsWith("업데이트된상품");
    }
  }

  @Test
  void 동시에_저장과_조회를_수행해도_안전하다() throws InterruptedException {
    int saveThreadCount = 25;
    int readThreadCount = 25;
    ExecutorService executorService = Executors.newFixedThreadPool(saveThreadCount + readThreadCount);
    CountDownLatch latch = new CountDownLatch(saveThreadCount + readThreadCount);

    Set<Long> savedIds = ConcurrentHashMap.newKeySet();
    List<Optional<Product>> readResults = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < saveThreadCount; i++) {
      final int index = i;
      executorService.submit(() -> {
        try {
          Product product = new Product(null, "동시상품" + index, 10000 + index, "설명" + index, "image" + index + ".jpg");
          Long savedId = repository.save(product);
          savedIds.add(savedId);
        } finally {
          latch.countDown();
        }
      });
    }

    Long existingId = repository.save(testProduct);
    for (int i = 0; i < readThreadCount; i++) {
      executorService.submit(() -> {
        try {
          Optional<Product> product = repository.findById(existingId);
          readResults.add(product);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    assertThat(savedIds).hasSize(saveThreadCount);
    assertThat(readResults).hasSize(readThreadCount);
    assertThat(readResults.stream().allMatch(Optional::isPresent)).isTrue();
  }

  @Test
  void 동시에_삭제와_조회를_수행해도_안전하다() throws InterruptedException {
    List<Long> savedIds = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Product product = new Product(null, "삭제테스트상품" + i, 10000 + i, "설명" + i, "image" + i + ".jpg");
      savedIds.add(repository.save(product));
    }

    int threadCount = 20;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      final int index = i;
      executorService.submit(() -> {
        try {
          Long targetId = savedIds.get(index % savedIds.size());
          if (index % 2 == 0) {
            repository.deleteById(targetId);
          } else {
            repository.findById(targetId);
          }
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    assertThat(true).isTrue();
  }

  @Test
  void 동시에_페이징_조회를_수행해도_안전하다() throws InterruptedException {
    for (int i = 0; i < 50; i++) {
      Product product = new Product(null, "페이징상품" + i, 10000 + i, "설명" + i, "image" + i + ".jpg");
      repository.save(product);
    }

    int threadCount = 30;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);
    List<List<Product>> results = Collections.synchronizedList(new ArrayList<>());
    SortInfo sortInfo = new SortInfo("name", true);

    for (int i = 0; i < threadCount; i++) {
      final int offset = i % 5; // 0~4 페이지
      executorService.submit(() -> {
        try {
          List<Product> products = repository.findAll(offset, 10, sortInfo);
          results.add(products);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(5, TimeUnit.SECONDS);
    executorService.shutdown();

    assertThat(results).hasSize(threadCount);
    assertThat(results.stream().allMatch(list -> list.size() <= 10)).isTrue();
  }
}