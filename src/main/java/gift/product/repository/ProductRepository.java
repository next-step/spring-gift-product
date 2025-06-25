package gift.product.repository;

import gift.common.dto.PagedResult;
import gift.common.exception.ErrorCode;
import gift.common.exception.InvalidSortFieldException;
import gift.product.domain.Product;
import gift.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final ConcurrentHashMap<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public PagedResult<Product> findAll(int page, int size, String sortField, boolean asc) {
        Comparator<Product> comparator = switch (sortField) {
            case "name" -> Comparator.comparing(Product::getName);
            case "price" -> Comparator.comparingInt(Product::getPrice);
            case "id" -> Comparator.comparing(Product::getId);
            default -> throw new InvalidSortFieldException(ErrorCode.INVALID_INPUT_VALUE);
        };

        if (!asc) comparator = comparator.reversed();

        List<Product> filtered = productMap.values().stream()
                .sorted(comparator)
                .toList();

        long total = filtered.size();
        int totalPages = (int) Math.ceil((double) total / size);
        boolean isFirst = page == 0;
        boolean isLast = page >= totalPages - 1;

        List<Product> pageContent = filtered.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PagedResult<>(
                pageContent,
                page,
                size,
                total,
                totalPages,
                isFirst,
                isLast
        );
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Long save(Product product) {
        Long id = idGenerator.incrementAndGet();
        Product newProduct = Product.of(id, product);
        productMap.put(id, newProduct);
        return id;
    }

    public void update(Long id, Product updatedProduct) {
        productMap.put(id, updatedProduct);
    }

    public Product delete(Long id) {
        return productMap.remove(id);
    }


}
