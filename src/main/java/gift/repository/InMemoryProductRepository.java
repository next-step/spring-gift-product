package gift.repository;

import gift.domain.Product;
import gift.dto.common.Page;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    private static final AtomicLong sequence = new AtomicLong(0);
    private static final int MIN_PAGE_NUMBER = 1;
    private static final int MIN_PAGE_SIZE = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public void save(Product product) {
        validateProductNotNull(product);
        Long id = generateId();
        Product productWithId = Product.withId(
                id,
                product.name(),
                product.price(),
                product.imageUrl()
        );
        products.put(id, productWithId);
    }

    @Override
    public void update(Long id, Product updatedProduct) {
        validateIdNotNull(id);
        validateProductNotNull(updatedProduct);
        validateExistsById(id);

        products.computeIfPresent(id, (key, existingProduct) ->
                existingProduct.update(
                        updatedProduct.name(),
                        updatedProduct.price(),
                        updatedProduct.imageUrl())
        );
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.forEach(products::remove);
    }

    @Override
    public void deleteById(Long id) {
        validateIdNotNull(id);
        validateExistsById(id);

        products.remove(id);
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public Page<Product> findAllByPage(int pageNumber, int pageSize) {
        validatePageParams(pageNumber, pageSize);
        if (pageSize < MIN_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        int total = products.size();
        int totalPages = calculateTotalPages(total, pageSize);

        if (pageNumber > totalPages) {
            return new Page<>(Collections.emptyList(), pageNumber, pageSize, total);
        }

        List<Product> allProducts = new ArrayList<>(products.values());
        
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);
        List<Product> pageContent = allProducts.subList(fromIndex, toIndex);

        return new Page<>(pageContent, pageNumber, pageSize, total);
    }

    @Override
    public Optional<Product> findById(Long id) {
        validateIdNotNull(id);
        return Optional.ofNullable(products.get(id));
    }

    private Long generateId() {
        return sequence.incrementAndGet();
    }

    private int calculateTotalPages(int totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    private void validatePageParams(int pageNumber, int pageSize) {
        if (pageNumber < MIN_PAGE_NUMBER) {
            throw new IllegalArgumentException("pageNumber는 1 이상이어야 합니다.");
        }
        if (pageSize < MIN_PAGE_SIZE) {
            throw new IllegalArgumentException("pageSize는 1 이상이어야 합니다.");
        }
    }

    private void validateIdNotNull(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("상품 ID가 null일 수 없습니다.");
        }
    }

    private void validateProductNotNull(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("상품 정보가 null일 수 없습니다.");
        }
    }

    private void validateExistsById(Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + id);
        }
    }
}
