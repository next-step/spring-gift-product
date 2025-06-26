package gift.api.repository;

import gift.api.domain.Product;
import gift.exception.ProductNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong currentProductId = new AtomicLong(1L);

    @Override
    public Page<Product> findAllProducts(Pageable pageable, Long categoryId) {
        // Step 1: 전체 스트림 준비
        Stream<Product> stream = products.values().stream();

        // Step 2: categoryId로 필터링 (나중에 필요시 주석 해제)
//        if (categoryId != null) {
//            stream = stream.filter(product -> categoryId.equals(product.getCategoryId()));
//        }

        // Step 3: 정렬
        if (pageable.getSort().isSorted()) {
            for (Sort.Order order : pageable.getSort()) {
                if (order.getProperty().equals("name")) {
                    Comparator<Product> comparator = Comparator.comparing(Product::getName);
                    if (order.isDescending()) {
                        comparator = comparator.reversed();
                    }
                    stream = stream.sorted(comparator);
                }
                if (order.getProperty().equals("price")) {
                    Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
                    if (order.isDescending()) {
                        comparator = comparator.reversed();
                    }
                    stream = stream.sorted(comparator);
                }
                // 필요 시 다른 정렬 필드도 추가
            }
        }

        // Step 4: 정렬된 리스트 만들기
        List<Product> sortedList = stream.toList();

        // Step 5: 페이지 계산
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sortedList.size());
        List<Product> pageContent = (start > end) ? List.of() : sortedList.subList(start, end);

        // Step 6: Page 객체로 감싸서 반환
        return new PageImpl<>(pageContent, pageable, sortedList.size());
    }

    @Override
    public Product findProductById(Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct = new Product(
                currentProductId.getAndIncrement(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());

        products.put(newProduct.getId(), newProduct);

        return newProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        Product newProduct = products.get(product.getId());

        if (newProduct == null) {
            throw new ProductNotFoundException(product.getId());
        }

        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());

        return newProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(id);
        }

        products.remove(id);
    }
}
