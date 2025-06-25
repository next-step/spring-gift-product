package gift.product.repository;

import gift.common.dto.PagedResult;
import gift.common.exception.ErrorCode;
import gift.product.domain.Product;
import gift.product.dto.GetProductResDto;
import gift.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final ConcurrentHashMap<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public PagedResult<Product> findAll(int page, int size, String sortField, boolean asc) {
        Comparator<Product> comparator = switch (sortField) {
            case "name" -> Comparator.comparing(Product::getName);
            case "price" -> Comparator.comparingInt(Product::getPrice);
            case "id" -> Comparator.comparing(Product::getId);
            default -> throw new IllegalArgumentException("정렬 필드 오류");
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

    public Product findById(Long id) throws ProductNotFoundException {
        Product product = productMap.get(id);
        if (product == null) {
            throw new ProductNotFoundException(ErrorCode.NOT_FOUND);
        }
        return product;
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

    public boolean delete(Long id) {
        return productMap.remove(id) != null;
    }


}
