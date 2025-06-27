package gift.product.repository;

import gift.common.dto.PagedResult;
import gift.common.exception.ErrorCode;
import gift.common.exception.InvalidSortFieldException;
import gift.common.strategy.SortStrategy;
import gift.product.domain.Product;
import gift.product.strategy.ProductSortStrategyFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

  private final ConcurrentHashMap<Long, Product> productMap = new ConcurrentHashMap<>();
  private final AtomicLong idGenerator = new AtomicLong();

  @Override
  public void save(Product product) {
    Objects.requireNonNull(product,"상품은 null일 수 없습니다");

    Long id = idGenerator.incrementAndGet();
    Product newProduct = Product.withId(id,product);
    productMap.put(id, newProduct);
  }

  @Override
  public Optional<Product> findById(Long id) {
    Objects.requireNonNull(id,"ID는 null일 수 없습니다");

    return Optional.ofNullable(productMap.get(id));
  }

  @Override
  public PagedResult<Product> findAll(int page, int size, String sortField, boolean asc) {
    Objects.requireNonNull(sortField, "정렬 필드값은 null일 수 없습니다");
    SortStrategy<Product> sortStrategy = ProductSortStrategyFactory.getStrategy(sortField);

    Comparator<Product> comparator = asc ?
        sortStrategy.getComparator() :
        sortStrategy.getComparator().reversed();

    List<Product> content = productMap.values().stream()
        .sorted(comparator)
        .skip((long) page * size)
        .limit(size)
        .toList();

    return PagedResult.of(content,page,size);

  }

  @Override
  public void update(Long id, Product updatedProduct) {
    Objects.requireNonNull(id,"ID는 null일 수 없습니다");
    Objects.requireNonNull(updatedProduct,"상품은 null일 수 없습니다");

    productMap.put(id, updatedProduct);
  }

  @Override
  public void deleteById(Long id) {
    Objects.requireNonNull(id,"id cannot be null");

    productMap.remove(id);
  }

}
