package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    Long nextId = 1L;

    public Product save(Product product) {

        Long id = nextId++;
        product.setId(id);
        products.put(id, product);

        return product;
    }

    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public Optional<Product> update(Long id, String name, Integer price, String imageUrl) {
        Product existingProduct = products.get(id);
        if (existingProduct == null) {
            return Optional.empty();
        }

        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setImageUrl(imageUrl);

        return Optional.of(existingProduct);
    }

    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }

    public List<Product> findAll(int page, int size, String sortField, String sortDir) {
        Comparator<Product> comparator;

        if ("name".equals(sortField)) {
            comparator = Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER);
        } else if ("price".equals(sortField)) {
            comparator = Comparator.comparing(Product::getPrice);
        } else {
            comparator = Comparator.comparing(Product::getId);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return products.values().stream()
                .sorted(comparator)
                .skip((long) page * size) // 페이지 지정
                .limit(size) // 개수 지정
                .toList();
    }
}
