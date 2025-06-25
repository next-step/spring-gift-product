package gift.repository;

import gift.domain.Product;
import gift.dto.common.Page;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private static final AtomicLong sequence = new AtomicLong(0);
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("저장할 상품 정보가 null입니다.");
        }

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
        if (id == null) {
            throw new IllegalArgumentException("수정할 상품 ID가 null입니다.");
        }
        if (updatedProduct == null) {
            throw new IllegalArgumentException("수정할 상품 정보가 null입니다.");
        }

        boolean updated =
                products.computeIfPresent(id, (key, existingProduct) -> existingProduct.update(
                        updatedProduct.name(),
                        updatedProduct.price(),
                        updatedProduct.imageUrl()
                )) != null;

        if (!updated) {
            throw new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + id);
        }
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        ids.forEach(products::remove);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("삭제할 상품 ID가 null입니다.");
        }

        Product removed = products.remove(id);
        if (removed == null) {
            throw new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + id);
        }
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public Page<Product> findAllByPage(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            return new Page<>(Collections.emptyList(), pageNumber, pageSize,
                    products.size());
        }

        if (pageSize < 1) {
            pageSize = 10;
        }

        int total = products.size();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        if (pageNumber > totalPages) {
            return new Page<>(Collections.emptyList(), pageNumber, pageSize, total);
        }

        List<Product> allProducts = List.copyOf(products.values()); // 정렬된 상태라 가정

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);

        List<Product> pageContent = allProducts.subList(fromIndex, toIndex);

        return new Page<>(pageContent, pageNumber, pageSize, total);
    }


    @Override
    public Optional<Product> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("조회할 상품 ID가 null입니다.");
        }
        return Optional.ofNullable(products.get(id));
    }

    private Long generateId() {
        return sequence.incrementAndGet();
    }
}
